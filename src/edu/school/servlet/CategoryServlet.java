package edu.school.servlet;

import edu.school.entity.Category;
import edu.school.entity.PageBean;
import edu.school.service.CategoryService;
import edu.school.service.impl.CategoryServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//公共使用的Servlet
public class CategoryServlet extends BaseServlet {
   private CategoryService cs=new CategoryServiceImpl();
    //添加分类
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String cname=request.getParameter("cname");
        System.out.println(cname);
        Category record=new Category();
        record.setCname(cname);
        cs.addCategory(record);
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?action=list");//重定向防止重复提交哦

    }

    protected void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        List<Category> list=cs.selectAll();
        writeValue(list,response);
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
        int currentCount = 4;
        String _currentCount = request.getParameter("currentCount");
        if (_currentCount != null) {
            currentCount = Integer.parseInt(_currentCount);
        }

        // 4.调用service，完成获取当前页分页Bean数据.

        PageBean bean = cs.findCategoryByPage(currentPage,currentCount);
        // 将数据存储到request范围，跳转到product_list.jsp页面展示
        request.setAttribute("bean", bean);
        request.getRequestDispatcher("/WEB-INF/admin/category_list.jsp").forward(request,response); //页面转发
        return;


    }
    //编辑分类信息表单回显
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id= WebUtils.parseInt(request.getParameter("id"),0);
         Category category=cs.findCategoryById(id);
         request.setAttribute("category",category);
         System.out.println(category);
         request.getRequestDispatcher("/WEB-INF/admin/edit_category.jsp").forward(request,response);
    }
    //删除分类信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        cs.deleteCategory(id);
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?action=list"); //重定向防止重复提交哦
    }


    //更改分类信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String cname=request.getParameter("cname");
        System.out.println(cname);
        cs.updateCategory(new Category(id,cname));
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?action=list");//重定向防止重复提交哦
    }

    //模糊查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String cname=request.getParameter("cname");
        List<Category> list=cs.findByMap(cname);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/admin/category_list.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/admin/list_category.jsp").forward(request, response);
        }
    }

    //跳转到添加或者编辑分类信息的页面
    protected void toEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        request.getRequestDispatcher("/WEB-INF/admin/edit_category.jsp").forward(request,response);
    }

}
