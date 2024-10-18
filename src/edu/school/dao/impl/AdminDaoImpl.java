package edu.school.dao.impl;/*
 *@BelongsProject：pm_sys
 *@BelongPackage:edu.school.dao.impl
 *@Description:超级管理员dao层 */

import edu.school.dao.AdminDao;
import edu.school.entity.Admin;
import edu.school.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public Admin findAdminByEmailAndPwd(String email, String pwd) {
        try {
            return runner.query("select * from admin where email=? and pwd=?", new BeanHandler<Admin>(Admin.class),email,pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int updatePwd(Admin record) {
        String sql="update admin set pwd=? where email=?";
        try {//执行更改
            runner.update(sql,record.getPwd(),record.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
        return 1;
    }

    @Override
    public Admin findByEmail(String email) {
        try {
            return runner.query("select * from admin where email=?", new BeanHandler<Admin>(Admin.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public int update(Admin admin) {
        String sql = "update admin set name=?,pwd=? where email=?";
        try {
            runner.update(sql, admin.getName(),admin.getPwd(),admin.getEmail());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return 1;
    }
}
