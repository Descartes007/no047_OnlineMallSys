package edu.school.dao.impl;/*
 *@BelongPackage:edu.school.dao.impl
 *@Description:todo
 */

import edu.school.dao.UserDao;
import edu.school.entity.User;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
   private  QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public void save(User user) {
        String sql = "insert into user(name,password,gender,email,telephone,address,registeTime) values(?,?,?,?,?,?,?)";
        try {
           runner.update(sql, user.getName(), user.getPassword(),
                    user.getGender(), user.getEmail(), user.getTelephone(),
                    user.getAddress(), user.getRegisteTime());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        String sql="select * from user where email=? and password=?";
        try {
            return runner.query(sql, new BeanHandler<User>(User.class),email,password);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public User queryByEmail(String email) {
        try {
            return runner.query("select * from user where email=?", new BeanHandler<User>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public User query(Integer id) {
        try {
            return runner.query("select * from user where id=?", new BeanHandler<User>(User.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int update(User user) {
        String sql = "update user set name=?,password=?,gender=?,email=?,telephone=?,address=? where id=?";
        try {
            runner.update(sql, user.getName(), user.getPassword(),
                    user.getGender(), user.getEmail(), user.getTelephone(),
                    user.getAddress(),user.getId());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from user where id=?";
        try {
            //执行删除的sql
            runner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;//删除成功返回1表示结束
    }

    @Override
    public int updatePwd(User user) {
        String sql="update user set password=? where email=?";
        try {//执行更改
            runner.update(sql,user.getPassword(),user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
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
    public List<User> findByPage(int currentPage, int currentCount) {
        String sql = "select * from user limit ?,?";
        Object[] obj = new Object[] { (currentPage - 1) * currentCount,
                currentCount, };
        try {
            return runner.query(sql, new BeanListHandler<User>(User.class), obj);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findByMap(String name) {
        String sql="select * from user  where 1=1";
        List<User> list=null;
        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
        List<String> list1 = new ArrayList<String>();

        if (name != "") {
            sql += " and name like  ? ";
            //将用户输入的参数添加到集合
            list1.add("%" + name + "%");
        }

        Object[] params = list1.toArray();
        try {
            list=runner.query(sql, params, new BeanListHandler<User>(User.class));
            System.out.println(list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
