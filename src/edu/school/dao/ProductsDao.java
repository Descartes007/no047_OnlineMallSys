package edu.school.dao;

import edu.school.entity.Products;

import java.util.List;

public interface ProductsDao {
    public void save(Products record);//添加
    List<Products> selectAll();//查询所有
    int findAllCount();//获取数据总条数
    int findTotalCount(int c_id);//根据c_id记录总记录数
    List<Products> findByPage(int currentPage, int currentCount);//后台分页查询
    List<Products> findByQtPage(int c_id, int start, int pageSize);//前台分页查询
    List<Products> getShouyePage(int start, int pageSize);//前台分页查询
    Products query(Integer id);//根据id查询
    int delete(Integer id);//根据id删除
    List<Products> findByMap(String name);//条件查询
    List<Products> findBySearch(String name);//前台关键字条件查询
    int update(Products record);//根据id修改信息
}
