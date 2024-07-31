package edu.school.servlet;
import edu.school.service.AdminService;
import edu.school.service.CategoryService;
import edu.school.service.impl.AdminServiceImpl;
import edu.school.service.impl.CategoryServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//公共使用的Servlet
public class CommonServlet extends BaseServlet {
    private CategoryService cs=new CategoryServiceImpl();
   private AdminService adminService=new AdminServiceImpl();

   //用户登录

    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        request.getRequestDispatcher("/WEB-INF/client/user_login.jsp").forward(request,response);
    }
    //去首页
    protected void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request,response);
    }

    //去购物车页面
    protected void toCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        System.out.println(id);
        request.getRequestDispatcher("/WEB-INF/client/cart.jsp").forward(request,response);
    }

    //去欢迎页
    protected void toWelcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request,response);
    }


    protected void toAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/views/userEdit.jsp").forward(request,response);
    }
    //注销登录
    protected void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//退出
        request.getSession().invalidate();
        request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request,response);
    }
}
