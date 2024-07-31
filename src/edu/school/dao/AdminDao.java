package edu.school.dao;/*
 *@BelongPackage:edu.school.dao
 *@Description:管理员dao
 */

import edu.school.entity.Admin;


public interface AdminDao {
    public Admin findAdminByEmailAndPwd(String email, String pwd); //根据邮箱与密码登录
    public int updatePwd(Admin record);//根据id修改管理员
    public Admin findByEmail(String email); //根据邮箱查询、
    public int update(Admin admin);//根据id修改竞拍者信息
}
