package com.zty.xiaomiadmin.server.Dao;

import com.zty.xiaomiadmin.server.Entity.diagram.LineChartGoodEntity;
import com.zty.xiaomiadmin.server.Entity.diagram.PageVisits;
import com.zty.xiaomiadmin.server.Entity.log.LogsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @pakage com.zty.xiaomiadmin.server.Dao
 * @auther 邮专第一深情
 * @Date 2023/8/15 8:51
 * @Descripetion
 */
@Mapper
public interface Log {

    @Select("SELECT  url , count(url) as `count` from `logs` GROUP BY url")
    List<PageVisits> selectPageVisits();

    @Select("SELECT count(user_id) as '访问次数' from (SELECT DISTINCT user_id from logs_user ) AS userid")
    Integer selectVisiterCount();
    @Select("SELECT log_id as logId ,order_id as orderId FROM logs_order LIMIT 0,10")
    List<LogsOrder> selectLogIdAndOrderId();

    @Select("select ip from logs where id = #{logId}")
    String selectIpByLogId(Integer logId);

    @Select("select count(*) from ordergood where orderNo = #{orderId}")
    int selectOrderGoodCountByOrderId(Integer orderId);

    @Select("select count(*) from logs where ip = #{ip} and url = '/product/getinfo' ")
    int logSelectViewCountByIp(String ip);
    @Select("SELECT a.good_id as goodId ,count(a.good_id) as count,b.category_id as categoryId FROM logs_good AS a" +
            " LEFT JOIN goods AS b ON a.good_id = b.good_id GROUP BY a.good_id")
    List<LineChartGoodEntity> selectLineChartGoodEntities();
}