package edu.school.servlet;


import edu.school.entity.Cart;
import edu.school.entity.Products;
import edu.school.entity.User;
import edu.school.service.CartService;
import edu.school.service.ProductsService;
import edu.school.service.impl.CartServiceImpl;
import edu.school.service.impl.ProductsServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
 *@BelongPackage:${PACKAGE_NAME}
 *@Author:admin

 */public class CartServlet extends BaseServlet {
     ProductsService service=new ProductsServiceImpl();
     CartService cs=new CartServiceImpl();
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String pid=request.getParameter("id");
        Products p=null;
        String count=request.getParameter("count");
        String url=request.getParameter("url");
        HttpSession session = request.getSession();
        String type=String.valueOf(session.getAttribute("type"));
        String name=String.valueOf(session.getAttribute("name"));
        System.out.println(type+name+count+url);
        User user = (User) session.getAttribute("user");
        if(user!=null&&type.equals("1")){
            int uid=user.getId();
            if(pid!=null){
               p=service.findById(Integer.parseInt(pid));
                Cart cart=new Cart(null,p.getImgUrl(),p.getName(),p.getPrice(),Integer.parseInt(count),p.getPnum(),p.getId(),uid);
                cs.addCart(cart);
            }
        }else{
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('请登录在购买');");
            out.println("location.href='/UserServlet?action=toLogin'");
            out.println("</script>");
            out.close();
            return;
        }
       if(url.equals("z")){
           response.sendRedirect(request.getContextPath()+"/CartServlet?action=list");//重定向防止重复提交哦
       }else if(url.equals("j")) {
           response.sendRedirect(request.getContextPath()+"/ProductsServlet?action=toDetail&id="+pid);//重定向防止重复提交哦
       }

    }
    //如果没有登录就跳转到用户登录界面
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        request.getRequestDispatcher("/WEB-INF/client/user_login.jsp").forward(request,response);
    }

    //购物车列表
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        HttpSession session = request.getSession();
        String type=String.valueOf(session.getAttribute("type"));
        User user = (User) session.getAttribute("user");

        if(user!=null&&type.equals("1")){
            int uid=user.getId();
            List<Cart> list=cs.findCartListByUid(uid);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/client/cart.jsp").forward(request, response);
        }else{
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('请登录在购买');");
            out.println("location.href='/CartServlet?action=toLogin'");
            out.println("</script>");
            out.close();

            return;
        }
        /*request.getRequestDispatcher("/WEB-INF/client/cart.jsp").forward(request,response);*/
    }
    //统计购物车数量
   /* protected void total(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        HttpSession session = request.getSession();
        String type=String.valueOf(session.getAttribute("type"));
        User user = (User) session.getAttribute("user");
        int totalCount=cs.totalCartCount(user.getId());
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("total", totalCount);
        System.out.println(totalCount);
        writeValue(testMap,response);
    }*/
    //改变购物车数量
    protected void updateCartNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        int count=Integer.parseInt(request.getParameter("count"));
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Cart cart=new Cart();
        cart.setQuantity(count);
        cart.setId(id);
        cs.updateQuantity(cart);
        response.sendRedirect(request.getContextPath()+"/CartServlet?action=list");//重定向防止重复提交哦
    }
    //购物车列表删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        cs.deleteCart(id);
        response.sendRedirect(request.getContextPath()+"/CartServlet?action=list"); //重定向防止重复提交哦
    }




}
