package edu.school.service.impl;/*

 *@Description:实现类
 *
 */

import edu.school.dao.UserDao;
import edu.school.dao.impl.UserDaoImpl;
import edu.school.entity.PageBean;
import edu.school.entity.User;
import edu.school.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao= new UserDaoImpl();

    @Override
    public void regieteUser(User user) {
        dao.save(user);
    }

    @Override
    public void addUser(User user) {
        dao.save(user);
    }

    @Override
    public User login(String email, String password) {
        return dao.findUserByEmailAndPassword(email,password);
    }

    @Override
    public User queryByEmail(String email) {
        return dao.queryByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateUser(User user) {
        return dao.update(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int updatePwd(User user) {
        return dao.updatePwd(user);
    }

    @Override
    public boolean existsEmail(String email) {
        if(dao.queryByEmail(email)==null){
            return false;//表示不可以用
        }
        return true;//表示可以用

    }

    @Override
    public PageBean findByUserPage(int currentPage, int currentCount) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        try {
            // 获取总条数
            int totalCount = dao.findAllCount();
            bean.setTotalCount(totalCount);
            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            // 获取当前页数据
            List<User> userList = dao.findByPage(currentPage,currentCount);
            bean.setuList(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public List<User> findByMap(String name) {
        return dao.findByMap(name);
    }
}
