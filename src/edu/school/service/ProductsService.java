package edu.school.service;/*
 *@BelongPackage:edu.school.service
 *@Description:拍卖物品业务逻辑层
 */

import edu.school.entity.PageBean;
import edu.school.entity.Products;
import edu.school.entity.QtPageBean;

import java.util.List;

public interface ProductsService  {
    public void addProducts(Products record);//添加
    List<Products> selectAll();//查询所有
    int findAllCount();//获取数据总条数
    PageBean findByProductsPage(int currentPage, int currentCount);//分页查询
    QtPageBean<Products> findByQtProductsPage(int c_id, int currentPage, int pageSize);//分页查询
    QtPageBean<Products> getShouyeProductsPage(int currentPage, int pageSize);//前台分页查询
    Products findById(Integer id);//根据id查询
    int deleteProducts(Integer id);//根据id删除
    List<Products> findByMap(String name);//条件查询
    List<Products> findBySearch(String name);//前台关键字条件查询
    int updateProducts(Products record);//根据id修改物品信息
}
