package edu.school.service;/*
 *@BelongPackage:edu.school.service
 *@Description:管理员业务层
 */

import edu.school.entity.Admin;


public interface AdminService {
    public Admin login(String email, String pwd); //根据邮箱与密码登录
    public int updatePwd(Admin record);//根据id
    public Admin findByEmail(String email); //根据邮箱查询
    public int updateAdmin(Admin admin);//修改管理员信息
}
