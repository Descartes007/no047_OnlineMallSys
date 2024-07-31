package edu.school.service.impl;/*

 *@BelongPackage:edu.school.service.impl
 *@Description:管理员业务层
 */

import edu.school.dao.AdminDao;
import edu.school.dao.impl.AdminDaoImpl;
import edu.school.entity.Admin;
import edu.school.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao dao=new AdminDaoImpl();
    @Override
    public Admin login(String email, String pwd) {
        return dao.findAdminByEmailAndPwd(email,pwd);
    }

    @Override
    public int updatePwd(Admin record) {
        return dao.updatePwd(record);
    }

    @Override
    public Admin findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return dao.update(admin);
    }
}
