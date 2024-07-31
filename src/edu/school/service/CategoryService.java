package edu.school.service;/*
 *@BelongPackage:edu.school.service
 *@Description:分类业务层
 */

import edu.school.entity.Category;
import edu.school.entity.PageBean;

import java.util.List;

public interface CategoryService {
    public void addCategory(Category record);//添加
    List<Category> selectAll();//查询所有
    int findAllCount();//获取数据总条数
    public PageBean findCategoryByPage(int currentPage, int currentCount);
    Category findCategoryById(Integer id);//根据id查询
    public int deleteCategory(Integer id);//根据id删除
    public List<Category> findByMap(String cname);//条件查询
    int updateCategory(Category record);//根据id修改分类信息

}
