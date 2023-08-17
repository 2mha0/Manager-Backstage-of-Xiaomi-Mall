package com.zty.xiaomiadmin.server.Service.Log;

import com.zty.xiaomiadmin.server.Dao.Log;
import com.zty.xiaomiadmin.server.Entity.diagram.LineChartGoodEntity;
import com.zty.xiaomiadmin.server.Entity.diagram.PageVisits;
import com.zty.xiaomiadmin.server.Entity.log.LogsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @pakage com.zty.xiaomiadmin.server.Service.Log
 * @auther 邮专第一深情
 * @Date 2023/8/15 8:51
 * @Descripetion
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private Log log;

    @Override
    public List<PageVisits> getPageVisits() {

        List<PageVisits> pageVisits = log.selectPageVisits();
        return pageVisits;
    }
    @Override
    public Integer getVisiterCount() {
        Integer visiterCount = log.selectVisiterCount();
        return visiterCount;
    }

    @Override
    public List<LogsOrder> getLogIdAndOrderId() {
        List<LogsOrder> ids = log.selectLogIdAndOrderId();
        return ids;
    }

    @Override
    public String getIpByLogId(Integer logId) {
        String ip = log.selectIpByLogId(logId);
        return ip;
    }

    @Override
    public int getOrderGoodCountByOrderId(Integer orderId) {
        int count = log.selectOrderGoodCountByOrderId(orderId);
        return count;
    }

    @Override
    public int getViewCountByIp(String ip) {
        int viewCount = log.logSelectViewCountByIp(ip);
        return viewCount;
    }
    @Override
    public List<LineChartGoodEntity> getLineChartGoodEntities() {
        List<LineChartGoodEntity> lineChartGoodEntities = log.selectLineChartGoodEntities();
        return lineChartGoodEntities;
    }
}
