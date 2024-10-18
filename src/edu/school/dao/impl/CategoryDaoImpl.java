package edu.school.dao.impl;/*
 *@BelongsProject：pm_sys
 *@BelongPackage:edu.school.dao.impl
 *@Description:todo
 */


import edu.school.dao.CategoryDao;
import edu.school.entity.Category;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public void save(Category record) {
        String sql = "insert into category(cname) values(?)";
        try {
            runner.update(sql, record.getCname());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Category> selectAll() {
        String sql = "select * from category";
        try {
        return runner.query(sql, new BeanListHandler<Category>(Category.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int findAllCount() {
        String sql = "select count(*) from category";
        try {
            Long count = (long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Category> findByPage(int currentPage, int currentCount) {
        String sql = "select * from category  limit ?,?";
        Object[] obj = new Object[] { (currentPage - 1) * currentCount,
                currentCount, };
        try {
            return runner.query(sql, new BeanListHandler<Category>(Category.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public Category query(Integer id) {
        try {
            return runner.query("select * from category where id=?", new BeanHandler<Category>(Category.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from category where id=?";
        try {
            //执行删除的sql
            runner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }

    @Override
    public List<Category> findByMap(String cname) {
        String sql="select * from category  where 1=1";
        List<Category> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();
        if (cname != "") {
            //如果用户输入的pname不为空，那需要进行字符串拼接
            sql += " and cname like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + cname + "%");
        }
        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Category>(Category.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category record) {
        String sql="update category set cname=? where id=?";
        try {//执行更改
            runner.update(sql,record.getCname(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }
}
