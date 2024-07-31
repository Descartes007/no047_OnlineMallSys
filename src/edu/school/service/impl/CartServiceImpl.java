package edu.school.service.impl;/*
 *@BelongPackage:edu.school.service.impl
 *@Description:购物车业务层
 */

import edu.school.dao.CartDao;
import edu.school.dao.impl.CartDaoImpl;
import edu.school.entity.Cart;
import edu.school.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao dao =new CartDaoImpl();
    @Override
    public void addCart(Cart cart) {
        dao.save(cart);

    }

    @Override
    public List<Cart> findCartListByUid(int uid) {
        return dao.findByUid(uid);
    }

    @Override
    public int totalCartCount(int uid) {
        return dao.totalCount(uid);
    }

    @Override
    public int updateQuantity(Cart record) {
        return dao.update(record);
    }

    @Override
    public int deleteCart(int id) {
        return dao.delete(id);
    }

    @Override
    public Cart findById(String id) {
        return dao.query(id);
    }




}
