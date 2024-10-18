package edu.school.dao.impl;/*
 *
 *@BelongPackage:edu.school.dao.impl
 *@Description:拍卖商品
 */

import edu.school.dao.ProductsDao;
import edu.school.entity.Products;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDaoImpl implements ProductsDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public void save(Products record) {
        String sql = "insert into products(pno,name,price,c_id,pnum,imgUrl,description,startDate) values(?,?,?,?,?,?,?,?)";
        try {
            runner.update(sql, record.getPno(),record.getName(),record.getPrice(),record.getC_id(),record.getPnum(),record.getImgUrl(),record.getDescription(),record.getStartDate());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Products> selectAll() {
        String sql = "select * from products";
        try {
            return runner.query(sql, new BeanListHandler<Products>(Products.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int findAllCount() {
        String sql = "select count(*) from products";
        try {
            Long count = (long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int findTotalCount(int c_id) {
        String sql ="select count(*) from products where c_id=?";
        try {
            Long count = (long) runner.query(sql, new ScalarHandler(),c_id);
            return count.intValue();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args){
        System.out.println(new ProductsDaoImpl().findByMap("茶杯"));
        System.out.println(new ProductsDaoImpl().findByQtPage(37,0,5).toString());

    }


    @Override
    public List<Products> findByQtPage(int c_id, int start, int pageSize) {
        String sql = "select * from products where 1=1";
        List params = new ArrayList<String>();
        StringBuilder sb=new StringBuilder(sql);
        if (c_id !=0) {
            sb.append(" and c_id = ? ");
            params.add(c_id);
        }

        sb.append(" limit ? , ? ");
        sql=sb.toString();
        params.add(start);
        params.add(pageSize);
        try {
            return runner.query(sql, params.toArray(), new BeanListHandler<Products>(Products.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Products> getShouyePage(int start, int pageSize) {
        String sql = "select * from products  limit ?,? ";
        List params = new ArrayList<String>();
        params.add(start);
        params.add(pageSize);
        try {
            return runner.query(sql, params.toArray(), new BeanListHandler<Products>(Products.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Products> findByPage(int currentPage, int currentCount) {
        String sql = "select * from products limit ?,?";
        Object[] obj = new Object[] { (currentPage - 1) * currentCount,
                currentCount, };
        try {
            return runner.query(sql, new BeanListHandler<Products>(Products.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }



    @Override
    public Products query(Integer id) {
        try {
            return runner.query("select * from products where id=?", new BeanHandler<Products>(Products.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from products where id=?";
        try {
            //执行删除的sql
            runner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }


    public List<Products> findByMap(String name) {
        String sql="select * from products  where 1=1";
        List<Products> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (name != "") {
            sql += " and name like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + name + "%");
        }

        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Products>(Products.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Products> findBySearch(String name) {
        String sql="select * from products  where 1=1";
        List<Products> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (name != "") {
            sql += " and name like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + name + "%");
        }

        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Products>(Products.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Products record) {
        String sql="update products set pno=?, name=?,price=?, c_id=?,pnum=?, imgUrl=?, description=? where id=?";
        try {//执行更改
            runner.update(sql, record.getPno(),record.getName(),record.getPrice(),record.getC_id(),record.getPnum(),record.getImgUrl(),record.getDescription(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }
}
