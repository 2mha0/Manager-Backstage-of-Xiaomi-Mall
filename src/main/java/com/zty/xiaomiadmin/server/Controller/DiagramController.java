package com.zty.xiaomiadmin.server.Controller;


import com.zty.xiaomiadmin.server.Entity.Good.good;
import com.zty.xiaomiadmin.server.Entity.diagram.*;
import com.zty.xiaomiadmin.server.Entity.log.LogsOrder;
import com.zty.xiaomiadmin.server.Service.Good.GoodServiceImp;
import com.zty.xiaomiadmin.server.Service.Log.LogService;
import com.zty.xiaomiadmin.server.utils.ChinaMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("diagram")
public class DiagramController {
    @Autowired
    private LogService logService;

    @Autowired
    private GoodServiceImp goodServiceImp;

    @GetMapping("/pageVisits")
    public List<PageVisits> pageVisits() {
//        System.out.println("柱状图一访问成功");
        List<PageVisits> pageVisits = logService.getPageVisits();
        return  pageVisits;
    }
    @GetMapping("/pageVisitorCount")
    public Integer pageVisitorCount() {
//        System.out.println("页面访问总数请求成功");
        Integer visiterCount = logService.getVisiterCount();
        return visiterCount;
    }
    // 处理鸡公图（中国地图）的请求
    @GetMapping("/getChinaMap")
    public List<ChinaMap> getChinaMap(){
//        System.out.println("中国地图访问成功");
        ChinaMapUtil util = new ChinaMapUtil();
        return util.generateMapData();
    }

    // 处理词频图的请求
    @GetMapping("/getWordFrequency")
    public Map<String,Integer> getWordFrequency() throws IOException {
//        System.out.println("词频图访问成功");
        List<good> goodName = goodServiceImp.getGoodName();
        Map<String,Integer> map = new HashMap<>();
        ChinaMapUtil util = new ChinaMapUtil();
        int listSize = goodName.size();
        for (int i = 0; i < listSize; i++) {
            String name = goodName.get(i).getName();
            int randWordFrequency = util.randomNumberGenerator(0,100);
            map.put(name,randWordFrequency);
        }
        return map;
    }
    /**
     * 下单转化率=提交订单/浏览商品页面
     */
    @GetMapping("/conversion")
    public List<VisiterOrderConversion> conversion() {
        //查询logs_order ，获取logid 和 orderid
//        System.out.println("柱状图二访问成功");
//        List<LogsOrder> ids = logService.getLogIdAndOrderId();
//        if (ids == null) {
//            return null;
//        }
//        List<VisiterOrderConversion> visiterOrderConversions = new ArrayList<>();
//        for (LogsOrder id :
//                ids) {
//            Integer logId = id.getLogId();
//            Integer orderId = id.getOrderId();
//            //根据logId查询用户ip
//            String ip = logService.getIpByLogId(logId);
//            //根据orderId查询该用户下单商品个数
//            int count = logService.getOrderGoodCountByOrderId(orderId);
//            //根据ip查询用户浏览商品次数
//            int viewCount = logService.getViewCountByIp(ip);
//            double conversion = 0.0;
//            DecimalFormat df = new DecimalFormat("0.0");
//            if (viewCount != 0) {
//                conversion = (double) count / (double)viewCount;
//            };
//            String format = df.format(conversion * 100) + "%";
//            VisiterOrderConversion visiterOrderConversion = new VisiterOrderConversion();
//            visiterOrderConversion.setIp(ip);
//            visiterOrderConversion.setConversion(format);
//            visiterOrderConversions.add(visiterOrderConversion);
//        }
        List<VisiterOrderConversion> visiterOrderConversions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            VisiterOrderConversion v = new VisiterOrderConversion();
            String proxyHost = "";
            for (int j = 0; j < 4; j++) {
                proxyHost += (random.nextInt(254) + 1 )+ "";
                // 随后个数字末尾不需要加 "."
                if (j != 3){
                    proxyHost += ".";
                }
            }
            v.setIp(proxyHost);

            // 生成1到40之间的随机双精度浮点数
            double randomDouble = random.nextDouble() * 40 + 1;
            // 格式化保留两位小数
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedDouble = decimalFormat.format(randomDouble);
            v.setConversion(formattedDouble + "%");
            visiterOrderConversions.add(v);
        }
//        v.setIp("127.0.0.1");
//        v.setConversion("2.4%");


        return visiterOrderConversions;
    }

    @GetMapping("/lineChart")
    public HashMap<Integer,LineChartEntityVO> getLineChart() {
//        System.out.println("折线图数据获取成功");
        //查询good_id , count(good_id),category_id
        List<LineChartGoodEntity> lineChartGoodEntities = logService.getLineChartGoodEntities();
        HashMap< Integer,LineChartEntityVO> result = new HashMap();
        HashMap<Integer , Integer> categoryGoodNum = new HashMap<>();
        for (LineChartGoodEntity lineChartGoodEntity :
                lineChartGoodEntities ) {
            LineChartEntityVO lineChartEntityVO = result.getOrDefault(lineChartGoodEntity.getCategoryId(), null);
            if (lineChartEntityVO == null) {
                categoryGoodNum.put(lineChartGoodEntity.getCategoryId(),1);
                LineChartEntityVO lineChartEntityVO1 = new LineChartEntityVO();
                //Integer goodId = lineChartGoodEntity.getGoodId();
                Integer count = lineChartGoodEntity.getCount();
                HashMap<Integer, Integer> goodIdAndCount = new HashMap<>();
                goodIdAndCount.put(1,count);
                categoryGoodNum.put(lineChartGoodEntity.getCategoryId(),categoryGoodNum.get(lineChartGoodEntity.getCategoryId())+1);
                lineChartEntityVO1.setGoodIdAndCount(goodIdAndCount);
                result.put(lineChartGoodEntity.getCategoryId(),lineChartEntityVO1);
            }
            else {
                Integer goodId = categoryGoodNum.get(lineChartGoodEntity.getCategoryId());
                //Integer goodId = lineChartGoodEntity.getGoodId();
                Integer count = lineChartGoodEntity.getCount();
                HashMap<Integer, Integer> goodIdAndCount = lineChartEntityVO.getGoodIdAndCount();
                goodIdAndCount.put(goodId,count);
                categoryGoodNum.put(lineChartGoodEntity.getCategoryId(),categoryGoodNum.get(lineChartGoodEntity.getCategoryId())+1);
            }

        }

        return result;
    }
}
