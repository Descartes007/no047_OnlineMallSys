package edu.school.service.impl;/*
 *
 *@Description:订单业务层
 */

import edu.school.dao.OrderDao;
import edu.school.dao.impl.OrderDaoImpl;
import edu.school.entity.Order;
import edu.school.entity.PageBean;
import edu.school.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao dao=new OrderDaoImpl();
    @Override
    public void addOrder(Order record) {
        dao.save(record);
    }

    @Override
    public int findAllCount() {
        return dao.findAllCount();
    }

    @Override
    public PageBean findByOrderPage(int currentPage, int currentCount) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        try {
            // 获取总条数
            int totalCount = dao.findAllCount();
            bean.setTotalCount(totalCount);
            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            // 获取当前页数据
            List<Order> os = dao.findByPage(currentPage, currentCount);
            bean.setoList(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    @Override
    public Order findByIdOrder(Integer id) {
        return dao.query(id);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }

    @Override
    public List<Order> findByOrderMap(String orderno) {
        return dao.findByMap(orderno);
    }

    @Override
    public int updateOrder(Order record) {
        return dao.update(record);
    }

    @Override
    public List<Order> findByMyOrder(int uid) {
        return dao.findByUid(uid);
    }
}
