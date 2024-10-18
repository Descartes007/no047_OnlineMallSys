package edu.school.dao;/*

 *@BelongPackage:edu.school.dao
 *@Description:
 */

import edu.school.entity.Category;

import java.util.List;

public interface CategoryDao {
    public void save(Category record);//添加
    List<Category> selectAll();//查询所有
    int findAllCount();//获取数据总条数
    List<Category> findByPage(int currentPage, int currentCount);//分页查询
    Category query(Integer id);//根据id查询
    int delete(Integer id);//根据id删除
    List<Category> findByMap(String cname);//条件查询
    int update(Category record);//根据id修改分类信息
}
