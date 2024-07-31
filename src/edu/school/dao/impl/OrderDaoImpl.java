package edu.school.dao.impl;/*

 *
 *@Description:todo
 */

import edu.school.dao.OrderDao;
import edu.school.entity.Order;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public void save(Order record) {
        String sql = "insert into orders(orderno,money,receiver,phone,status,address,createTime,uid) values(?,?,?,?,?,?,?,?)";
        try {
            runner.update(sql, record.getOrderno(),record.getMoney(),record.getReceiver(),record.getPhone(),record.getStatus(),record.getAddress(),record.getCreateTime(),record.getUid());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int findAllCount() {
        String sql = "select count(*) from orders";
        try {
            Long count = (long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Order> findByPage(int currentPage, int currentCount) {
        String sql = "select * from orders limit ?,?";
        Object[] obj = new Object[] { (currentPage - 1) * currentCount,
                currentCount, };
        try {
            return runner.query(sql, new BeanListHandler<Order>(Order.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Order query(Integer id) {
        try {
            return runner.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from orders where id=?";
        try {
            //执行删除的sql
            runner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }

    @Override
    public List<Order> findByMap(String orderno) {
        String sql="select * from orders  where 1=1";
        List<Order> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (orderno != "") {
            sql += " and orderno like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + orderno + "%");
        }

        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<Order>(Order.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Order record) {
        String sql="update orders set receiver=?, phone=?,address=? ,status=? where id=?";
        try {//执行更改
            runner.update(sql, record.getReceiver(),record.getPhone(),record.getAddress(),record.getStatus(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public List<Order> findByUid(int uid) {
        String sql = "select * from orders where uid=?";
        Object[] obj = new Object[] { uid };
        try {
            return runner.query(sql, new BeanListHandler<Order>(Order.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
