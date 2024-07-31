package edu.school.service.impl;/*
 *@BelongPackage:edu.school.service.impl
 *@Description:业务层实现类
 */

import edu.school.dao.CategoryDao;
import edu.school.dao.impl.CategoryDaoImpl;
import edu.school.entity.Category;
import edu.school.entity.PageBean;
import edu.school.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao =new CategoryDaoImpl();
    @Override
    public void addCategory(Category record) {
        dao.save(record);
    }

    @Override
    public List<Category> selectAll() {
        return dao.selectAll();
    }

    @Override
    public int findAllCount() {
        return dao.findAllCount();
    }



    @Override
    public Category findCategoryById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int deleteCategory(Integer id) {
        return dao.delete(id);
    }

    @Override
    public List<Category> findByMap(String cname) {
        return dao.findByMap(cname);
    }

    @Override
    public int updateCategory(Category record) {
        return dao.update(record);
    }

    public PageBean findCategoryByPage(int currentPage, int currentCount) {
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
            List<Category> categoryList = dao.findByPage(currentPage, currentCount);
            bean.setcList(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
