package com.zty.xiaomiadmin.server.utils;

import com.zty.xiaomiadmin.server.Entity.diagram.ChinaMap;

import java.util.*;

/**
 * 中国地图工具类：负责生成地图数据，将地区热度分为冷门、一般、热门，数据对应地图热门程度生成
 */
public class ChinaMapUtil {
    public static final String[] PROVINCES =
            {"上海","北京","广东","山东","浙江","河南","四川","湖北",
            "福建","湖南","安徽","江苏","台湾","天津","河北",
            "陕西","江西","重庆","香港","辽宁","云南","广西","澳门",
            "山西","内蒙古","贵州","海南","黑龙江","吉林","甘肃",
            "青海","西藏","宁夏","新疆","南海诸岛"};

    private enum Rank{
        EXTREMELY_HIGH,HIGH,MEDIUM,LOW,EXTREMELY_LOW
    }

    private Rank whichRank(int provincesIndex){
        if (provincesIndex <= 7){
            return Rank.EXTREMELY_HIGH;
        }else if(provincesIndex <= 14){
            return Rank.HIGH;
        }else if(provincesIndex <= 22){
            return Rank.MEDIUM;
        }else if (provincesIndex <= 29){
            return Rank.LOW;
        }else {
            return Rank.EXTREMELY_LOW;
        }
    }

    // 生成的范围为:[min,max]
    public int randomNumberGenerator(int min,int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // 根据等级生成对应的随机数
    public int randomNumberGenerator(int min,int max,Rank rank){
        int result = 0;
        switch (rank){
            case EXTREMELY_HIGH:
                result = randomNumberGenerator(60,100);
                break;
            case HIGH:
                result = randomNumberGenerator(60,80);
                break;
            case MEDIUM:
                result = randomNumberGenerator(40,60);
                break;
            case LOW:
                result = randomNumberGenerator(20,40);
                break;
            case EXTREMELY_LOW:
                result = randomNumberGenerator(0,20);
                break;
        }
        return result;
    }

    // 生成每个省的随机数据
    public List<ChinaMap> generateMapData(){
        // 每个省份的等级
        Rank level;
        List<ChinaMap> data = new ArrayList<>();
        //每个省的用户数量后面通过随机数生成
        int userNum = 0;

        int provinceNum = PROVINCES.length;
        for (int i = 0; i < provinceNum; i++) {
            // 根据不同的等级获得不同的用户数
            level = whichRank(i);
            userNum = randomNumberGenerator(0,100,level);

            // 保存到ChinaMap实体类中在将该实体类放入list中
            ChinaMap chinaMap = new ChinaMap();
            chinaMap.setPlace(PROVINCES[i]);
            chinaMap.setPageView(userNum);
            data.add(chinaMap);
        }
        return data;
    }

    public static void main(String[] args) {
        ChinaMapUtil util = new ChinaMapUtil();
        System.out.println(util.generateMapData());
    }
}
