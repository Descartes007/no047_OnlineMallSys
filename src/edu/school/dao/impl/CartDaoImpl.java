package edu.school.dao.impl;/*
 *@BelongsProject：pm_sys
 *@BelongPackage:edu.school.dao.impl
 *@Description:购物车后台代码
 */

import edu.school.dao.CartDao;
import edu.school.entity.Cart;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    @Override
    public void save(Cart cart) {
        String sql = "insert into cart(profile,name,price,quantity,stock,pid,uid) values(?,?,?,?,?,?,?)";
        try {
            runner.update(sql,cart.getProfile(),cart.getName(),cart.getPrice(),cart.getQuantity(),cart.getStock(),cart.getPid(),cart.getUid());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Cart> findByUid(int uid) {
        String sql = "select * from cart  where uid=?";
        Object[] obj = new Object[] {uid};
        try {
            return runner.query(sql, new BeanListHandler<Cart>(Cart.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int totalCount(int uid) {
        String sql ="select count(*) from cart where uid=?";
        try {
            Long count = (long) runner.query(sql, new ScalarHandler(),uid);
            return count.intValue();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int update(Cart record) {
        String sql="update cart set quantity=? where id=?";
        try {//执行更改
            runner.update(sql,record.getQuantity(),record.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public int delete(int id) {
        String sql="delete from cart where id=?";
        try {
            //执行删除的sql
            runner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }

    @Override
    public Cart query(String id) {
        try {
            return runner.query("select * from cart where id=?", new BeanHandler<Cart>(Cart.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛出运行异常
        }
    }


}
