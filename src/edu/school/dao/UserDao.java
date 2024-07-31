package edu.school.dao;/*
 *@BelongPackage:edu.school.dao
 *@Description:用户
 */

import edu.school.entity.User;

import java.util.List;

public interface UserDao {
    public void save(User user);//注册
    public User findUserByEmailAndPassword(String email, String password); //根据邮箱与密码查找
    public User queryByEmail(String email);//根据邮箱查询
    public User query(Integer id);//根据id查询
    public int update(User user);//根据id修改
    public int delete(Integer id);//根据id删除
    public int updatePwd(User user);//根据id修改
    int findAllCount();//获取数据总条数
    List<User> findByPage(int currentPage, int currentCount);//后台分页查询
    List<User> findByMap(String name);//条件查询
}
