package com.zty.xiaomiadmin.server.Service.Log;

import com.zty.xiaomiadmin.server.Entity.diagram.LineChartGoodEntity;
import com.zty.xiaomiadmin.server.Entity.diagram.PageVisits;
import com.zty.xiaomiadmin.server.Entity.log.LogsOrder;

import java.util.HashMap;
import java.util.List;

/**
 * @pakage com.zty.xiaomiadmin.server.Service.Log
 * @auther 邮专第一深情
 * @Date 2023/8/15 8:50
 * @Descripetion
 */
public interface LogService {
    List<PageVisits> getPageVisits();
    Integer getVisiterCount();

    List<LogsOrder> getLogIdAndOrderId();

    String getIpByLogId(Integer logId);

    int getOrderGoodCountByOrderId(Integer orderId);

    int getViewCountByIp(String ip);
    List<LineChartGoodEntity> getLineChartGoodEntities();
}
