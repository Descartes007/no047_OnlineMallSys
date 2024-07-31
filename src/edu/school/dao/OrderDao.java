package edu.school.dao;/*
 *@BelongPackage:edu.school.dao
 *@Author:admin

 */


import edu.school.entity.Order;

import java.util.List;

public interface OrderDao {
    public void save(Order record);//添加
    int findAllCount();//获取数据总条数
    List<Order> findByPage(int currentPage, int currentCount);//后台分页查询
    Order query(Integer id);//根据id查询
    int delete(Integer id);//根据id删除
    List<Order> findByMap(String orderno);//条件查询
    int update(Order record);//根据id修改物品信息
    List<Order> findByUid(int uid);//根据uid条件查询我的订单
}
