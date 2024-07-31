package edu.school.servlet;

import edu.school.entity.PageBean;
import edu.school.entity.User;
import edu.school.service.UserService;
import edu.school.service.impl.UserServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class UserServlet extends BaseServlet {
   private UserService service=new UserServiceImpl();

   //登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理登录
        String email = request.getParameter("email");
        String password = request.getParameter("password");//获取密码
        String check = request.getParameter("che");//是否选择免登录
        //处理业务
        //1:判断是否选择三天免登录
        User user = service.login(email, password);
        if (user == null) {//如果用户不存在
            request.setAttribute("msg", "邮箱名或者密码错误");
            System.out.println("邮箱名或者密码错误");
            request.getRequestDispatcher("/WEB-INF/client/user_login.jsp").forward(request, response);
        } else { //如果用户存在且密码正确则进行将用户的信息保存
            if (check != null && "yes".equals(check)) {
                System.out.println(check);
                Cookie cookie1 = new Cookie("email", email);
                Cookie cookie2 = new Cookie("password", password);
                //设置cookie的有效期为3天，如果7天则保存为7
                cookie1.setMaxAge(60 * 60 * 24 * 3);
                cookie2.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                request.getSession().setAttribute("type", 1);
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("flag", 1);
                request.getSession().setAttribute("uid", user.getId());
                request.getSession().setAttribute("name", user.getName());
                request.getSession().setAttribute("phone", user.getTelephone());
                request.getSession().setAttribute("address", user.getAddress());
                request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request,response);
                System.out.println("登录成功");
            } else {
                request.getSession().setAttribute("type", 1);
                System.out.println(user.getName()+user.getEmail());
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("flag", 1);
                request.getSession().setAttribute("uid", user.getId());
                request.getSession().setAttribute("name", user.getName());
                request.getSession().setAttribute("phone", user.getTelephone());
                request.getSession().setAttribute("address", user.getAddress());
                request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request,response);
            }
        }
    }

    //分页查询
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        // 1.定义当前页码，默认为1
        int currentPage = 1;
        String _currentPage = request.getParameter("currentPage");
        if (_currentPage != null) {
            currentPage = Integer.parseInt(_currentPage);
        }
        // 2.定义每页显示条数,默认为4
        int currentCount = 5;
        String _currentCount = request.getParameter("currentCount");
        if (_currentCount != null) {
            currentCount = Integer.parseInt(_currentCount);
        }
        // 4.调用service，完成获取当前页分页Bean数据.
        PageBean bean =  service.findByUserPage(currentPage,currentCount);
        request.setAttribute("bean", bean);
        request.getRequestDispatcher("/WEB-INF/admin/user_list.jsp").forward(request,response); //页面转发
        return;

    }

//添加用户
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String name=request.getParameter("name").trim();
        String password=request.getParameter("password").trim();
        String gender=request.getParameter("gender").trim();
        String email=request.getParameter("email").trim();
        String telephone=request.getParameter("telephone").trim();
        String address=request.getParameter("address").trim();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String registeTime=sdf.format(dt);
        service.addUser(new User(null,name,password,gender,email,telephone, address,registeTime));
        response.sendRedirect(request.getContextPath()+"/UserServlet?action=list");//重定向防止重复提交哦


    }
    //表单回显
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id= WebUtils.parseInt(request.getParameter("id"),0);
         User user=service.findById(id);
         request.setAttribute("user",user);
         System.out.println(user);
         request.getRequestDispatcher("/WEB-INF/admin/edit_user.jsp").forward(request,response);
    }




    //更改用户信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String name=request.getParameter("name").trim();
        String password=request.getParameter("password").trim();
        String gender=request.getParameter("gender").trim();
        String email=request.getParameter("email").trim();
        String telephone=request.getParameter("telephone").trim();
        String address=request.getParameter("address").trim();
        String type=request.getParameter("type");
        if (type.equals("1")) {
            service.updateUser(new User(id,name,password,gender,email,telephone,address,null));
            request.getRequestDispatcher("/WEB-INF/client/updatemyinfo_ok.jsp").forward(request,response);
        }else {
            service.updateUser(new User(id,name,password,gender,email,telephone,address,null));
            response.sendRedirect(request.getContextPath()+"/UserServlet?action=list");//重定向防止重复提交哦
        }
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteUser(id);
        response.sendRedirect(request.getContextPath()+"/UserServlet?action=list"); //重定向防止重复提交哦
    }

    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String name=request.getParameter("name");
        List<User> list=service.findByMap(name);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/admin/user_list.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/admin/list_user.jsp").forward(request, response);
        }
    }


    //处理注册
    protected void registe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        String zsxm=request.getParameter("zsxm").trim();
        String password=request.getParameter("password").trim();
        String gender=request.getParameter("gender").trim();
        String email=request.getParameter("email").trim();
        String telephone=request.getParameter("telephone").trim();
        String address=request.getParameter("address").trim();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String registeTime=sdf.format(dt);
        String verifycode=request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println(verifycode+checkcode_server);
        //session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/WEB-INF/client/registe.jsp").forward(request,response);
            return;
        }

        if(service.existsEmail(email)){
            request.setAttribute("msg","邮箱已存在！");
            request.setAttribute("email",email);
            request.setAttribute("name",zsxm);
            request.setAttribute("telephone",telephone);
            request.getRequestDispatcher("/WEB-INF/client/registe.jsp").forward(request,response);
        }else{
            service.regieteUser(new User(null,zsxm,password,gender,email,telephone, address,registeTime));
            request.getRequestDispatcher("/WEB-INF/client/registe_success.jsp").forward(request,response);

        }


    }


    protected void toRegiste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        request.getRequestDispatcher("/WEB-INF/client/registe.jsp").forward(request,response);
    }



    //跳转到竞拍方登录界面
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        request.getRequestDispatcher("/WEB-INF/client/user_login.jsp").forward(request,response);
    }
    protected void toEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/admin/edit_user.jsp").forward(request,response);
    }
    //我的信息
    protected void MyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显

        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");
        User user=service.findById(user1.getId());
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/client/my_info.jsp").forward(request,response);

    }
   /* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
