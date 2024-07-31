package edu.school.service;/*
 *
 *@Description:todo
 */

import edu.school.entity.Order;
import edu.school.entity.PageBean;

import java.util.List;

public interface OrderService {
    public void addOrder(Order record);//添加
    int findAllCount();//获取数据总条数
    PageBean findByOrderPage(int currentPage, int currentCount);//后台分页查询
    Order findByIdOrder(Integer id);//根据id查询
    int delete(Integer id);//根据id删除
    List<Order> findByOrderMap(String orderno);//条件查询
    int updateOrder(Order record);//根据id修改物品信息
    List<Order> findByMyOrder(int uid);//根据uid条件查询我的订单
}
