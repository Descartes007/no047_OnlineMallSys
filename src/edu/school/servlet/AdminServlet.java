package edu.school.servlet;

import edu.school.entity.Admin;
import edu.school.service.AdminService;
import edu.school.service.impl.AdminServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AdminServlet extends BaseServlet {
   private AdminService service=new AdminServiceImpl();

   //管理员登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理登录
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();//获取密码
        Admin admin = service.login(email, password);
        String verifycode=request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println(verifycode+checkcode_server);
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
            return;
        }
        if (admin == null) {//如果用户不存在
            request.setAttribute("msg", "邮箱名或者密码错误");
            System.out.println("邮箱名或者密码错误");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else { //如果用户存在且密码正确则进行将用户的信息保存
            request.getSession().setAttribute("type", 2);
            request.getSession().setAttribute("admin", admin);
            request.getSession().setAttribute("email", admin.getEmail());
            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request,response);
        }
    }

    //跳转到管理员登录界面
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
    }

    //跳转到管理员信息
    protected void findAdminInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        String email=request.getParameter("email");
        Admin admin=service.findByEmail(email);
        request.setAttribute("admin",admin);
        System.out.println(admin);
        request.getRequestDispatcher("/WEB-INF/admin/edit_adminInfo.jsp").forward(request,response);
    }
    //更改管理员的昵称和密码
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String name=request.getParameter("name").trim();
        String pwd=request.getParameter("pwd").trim();
        String email=request.getParameter("email").trim();
        service.updateAdmin(new Admin(null,name,pwd,email));
        response.sendRedirect(request.getContextPath()+"/CommonServlet?action=toWelcome");//重定向防止重复提交哦

    }

}
