package edu.school.servlet;


import edu.school.entity.*;
import edu.school.service.CartService;
import edu.school.service.OrderService;
import edu.school.service.ProductsService;
import edu.school.service.impl.CartServiceImpl;
import edu.school.service.impl.OrderServiceImpl;
import edu.school.service.impl.ProductsServiceImpl;
import edu.school.utils.OnoUtil;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@BelongPackage:${PACKAGE_NAME}
 处理订单的业务

 */public class OrderServlet extends BaseServlet {
        ProductsService service=new ProductsServiceImpl();
        CartService cs=new CartServiceImpl();
        OrderService os=new OrderServiceImpl();

        //下订单
    protected void toOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String ids=request.getParameter("ids");
        Products p=null;
        HttpSession session = request.getSession();
        String type=String.valueOf(session.getAttribute("type"));
        String name=String.valueOf(session.getAttribute("name"));
        System.out.println(type+name);
        User user = (User) session.getAttribute("user");
        if(user!=null&&type.equals("1")){
            double totalPrice=0;
            String id[]=ids.split(",");
            List<Cart> list=new ArrayList<Cart>();
            for (int i=0;i<id.length;i++){
                    Cart c=cs.findById(id[i]);
                    double dprice=c.getPrice()*c.getQuantity();
                    totalPrice+=dprice;
                    list.add(c);
            }
            request.setAttribute("totalPrice",totalPrice);
            request.setAttribute("orderno",new OnoUtil().getOno());
            request.setAttribute("phone",user.getTelephone());
            request.setAttribute("address",user.getAddress());
            System.out.println("总金额:"+totalPrice);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/WEB-INF/client/order.jsp").forward(request, response);
        }else{
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('请登录在购买');");
            out.println("location.href='/CartServlet?action=toLogin'");
            out.println("</script>");
            out.close();
            return;
        }
    }

    //下订单
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String orderno=request.getParameter("orderno");
        double money=Double.parseDouble(request.getParameter("totalPrice"));
        String receiver=request.getParameter("receiver");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");
        HttpSession session = request.getSession();
        String type=String.valueOf(session.getAttribute("type"));
        String name=String.valueOf(session.getAttribute("name"));
        System.out.println(type+name);
        User user = (User) session.getAttribute("user");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String create_date = dateFormat.format(date);
        os.addOrder(new Order(null,orderno,money,receiver,address,phone,1,create_date,user.getId()));
        request.getRequestDispatcher("/WEB-INF/client/pay_ok.jsp").forward(request, response);

    }

    //修改订单
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String receiver=request.getParameter("receiver");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");
        int status=Integer.parseInt(request.getParameter("status"));
        Order order=new Order();
        order.setReceiver(receiver);
        order.setPhone(phone);
        order.setAddress(address);
        order.setStatus(status);
        order.setId(id);
        os.updateOrder(order);
        response.sendRedirect(request.getContextPath()+"/OrderServlet?action=list");//重定向防止重复提交哦

    }


    //后台订单列表
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        // 1.定义当前页码，默认为1
        int currentPage = 1;
        String _currentPage = request.getParameter("currentPage");
        if (_currentPage != null) {
            currentPage = Integer.parseInt(_currentPage);
        }
        // 2.定义每页显示条数,默认为4
        int currentCount = 4;
        String _currentCount = request.getParameter("currentCount");
        if (_currentCount != null) {
            currentCount = Integer.parseInt(_currentCount);
        }
        // 4.调用service，完成获取当前页分页Bean数据.
        PageBean bean = os.findByOrderPage(currentPage,currentCount);
        // 将数据存储到request范围，跳转到product_list.jsp页面展示
        request.setAttribute("bean", bean);
        request.getRequestDispatcher("/WEB-INF/admin/order_list.jsp").forward(request,response); //页面转发
        return;
    }


    //删除订单
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        os.delete(id);
        response.sendRedirect(request.getContextPath()+"/OrderServlet?action=list"); //重定向防止重复提交哦
    }

    //根据订单编号查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String orderno=request.getParameter("orderno").trim();
        System.out.println(orderno);
        List<Order> list=os.findByOrderMap(orderno);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/admin/order_list.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/admin/list_order.jsp").forward(request, response);
        }
    }

    //查询我的订单
    protected void findMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> list=os.findByMyOrder(user.getId());
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"没有订单信息");
            request.getRequestDispatcher("/WEB-INF/client/my_order.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/client/my_order.jsp").forward(request, response);
        }
    }
    //回显编辑订单
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Order order=os.findByIdOrder(id);
        request.setAttribute("order",order);
        System.out.println(order);
        request.getRequestDispatcher("/WEB-INF/admin/edit_order.jsp").forward(request,response);
    }




}
