package edu.school.dao;/*
 *@BelongPackage:edu.school.dao
 *@Description:购物车
 */

import edu.school.entity.Cart;

import java.util.List;

public interface CartDao {
    public void save(Cart cart);//添加购物车
    List<Cart> findByUid(int uid);//购物车列表
    int totalCount(int uid);//根据uid统计购物车数量
    int update(Cart record);//改变购物车数量
    int delete(int id);//根据id删除
    Cart query(String id);//根据id来判断
}
