package edu.school.service.impl;/*
 *@BelongPackage:edu.school.service.impl
 *@Description:拍卖物品业务层实现类
 */

import edu.school.dao.ProductsDao;
import edu.school.dao.impl.ProductsDaoImpl;
import edu.school.entity.PageBean;
import edu.school.entity.Products;
import edu.school.entity.QtPageBean;
import edu.school.service.ProductsService;

import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    private ProductsDao dao=new ProductsDaoImpl();
    @Override
    public void addProducts(Products record) {
        dao.save(record);
    }

    @Override
    public List<Products> selectAll() {
        return dao.selectAll();
    }

    @Override
    public int findAllCount() {
        return dao.findAllCount();
    }

    @Override
    public PageBean findByProductsPage(int currentPage, int currentCount) {
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
            List<Products> productsList = dao.findByPage(currentPage,currentCount);
            bean.setpList(productsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public QtPageBean<Products> findByQtProductsPage(int c_id, int currentPage, int pageSize) {
        QtPageBean<Products> bean = new QtPageBean<Products>();
        // 封装每页显示数据条数
        bean.setCurrentPage(currentPage);
        // 封装当前页码
        bean.setPageSize(pageSize);
        int totalCount=dao.findTotalCount(c_id);
        bean.setTotalCount(totalCount);
        int start= (currentPage-1)*pageSize;
        List<Products> list = dao.findByQtPage(c_id,start,pageSize);
        bean.setList(list);
        int totalPage=totalCount %pageSize ==0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        bean.setTotalPage(totalPage);
        return bean;
    }

    @Override
    public QtPageBean<Products> getShouyeProductsPage(int currentPage, int pageSize) {
        QtPageBean<Products> bean = new QtPageBean<Products>();
        // 封装每页显示数据条数
        bean.setCurrentPage(currentPage);
        // 封装当前页码
        bean.setPageSize(pageSize);
        int totalCount=dao.findAllCount();
        bean.setTotalCount(totalCount);
        int start= (currentPage-1)*pageSize;
        List<Products> list = dao.getShouyePage(start,pageSize);
        bean.setList(list);
        int totalPage=totalCount %pageSize ==0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        bean.setTotalPage(totalPage);
        return bean;
    }

    @Override
    public Products findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int deleteProducts(Integer id) {
        return dao.delete(id);
    }

    @Override
    public List<Products> findByMap(String name) {
        return dao.findByMap(name);
    }

    @Override
    public List<Products> findBySearch(String name) {
        return dao.findBySearch(name);
    }

    @Override
    public int updateProducts(Products record) {
        return dao.update(record);
    }
}
