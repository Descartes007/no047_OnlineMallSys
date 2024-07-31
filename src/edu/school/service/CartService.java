package edu.school.service;/*
 *@BelongPackage:edu.school.service
 *@Description:购物车业务层
 */

import edu.school.entity.Cart;

import java.util.List;

public interface CartService {
    public  void addCart(Cart cart);
    List<Cart> findCartListByUid(int uid);
    int totalCartCount(int uid);//根据uid统计购物车记录数
    int updateQuantity(Cart record);//改变购物车数量
    int deleteCart(int id);//根据id删除
    Cart findById(String id);//根据id来判断
}
