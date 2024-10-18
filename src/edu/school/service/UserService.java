package edu.school.service;/*
 *@BelongPackage:edu.school.service
 *@Description:竞拍方业务层
 */

import edu.school.entity.PageBean;
import edu.school.entity.User;

import java.util.List;

public interface UserService {
    public void regieteUser(User user);//注册用户
    public void addUser(User user);//注册用户
    public User login(String email, String password); //根据邮箱与密码查找用户
    public User queryByEmail(String email);//根据邮箱查询用户信息
    public User findById(Integer id);
    public int updateUser(User user);
    public int deleteUser(Integer id);//根据id
    public int updatePwd(User user);//根据id
    public boolean existsEmail(String email);//判断邮箱是否已经注册
    PageBean findByUserPage(int currentPage, int currentCount);//分页查询
    List<User> findByMap(String name);//条件查询

}
