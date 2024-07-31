package edu.school.servlet;

import edu.school.entity.Category;
import edu.school.entity.PageBean;
import edu.school.entity.Products;
import edu.school.entity.QtPageBean;
import edu.school.service.CategoryService;
import edu.school.service.ProductsService;
import edu.school.service.impl.CategoryServiceImpl;
import edu.school.service.impl.ProductsServiceImpl;
import edu.school.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//公共使用的Servlet
public class ProductsServlet extends BaseServlet {
   private ProductsService ps=new ProductsServiceImpl();
    private CategoryService cs=new CategoryServiceImpl();

    public String makeFileName(String filename){
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    //添加
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加

       System.out.println("请求已经到达");
       try {
            Products products = doupLoad(request);
           Date date = new Date();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String create_date = dateFormat.format(date);
           products.setStartDate(create_date);
            ps.addProducts(products);
           response.sendRedirect(request.getContextPath()+"/ProductsServlet?action=list");//重定向防止重复提交哦
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加


        System.out.println("请求已经到达");
        try {
            Products products = doupLoad(request);
            ps.updateProducts(products);
            response.sendRedirect(request.getContextPath()+"/ProductsServlet?action=list");//重定向防止重复提交哦
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    //上传图片
    private Products doupLoad(HttpServletRequest request) {
        Products products=new Products();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(products, name, value);
                }else{
                    String filename = item.getName();
                    String savefilename = makeFileName(filename);//
                    String savepath= this.getServletContext().getRealPath("/upload");
                  //  String savepath="G:\\shop\\web\\upload";
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    while((len = in.read(buffer)) > 0){
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    products.setImgUrl(savefilename);

                    System.out.println(products.getImgUrl());
                }
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void getShouyeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){//如果传递的参数不为空，则传入的值作为页面显示的数量
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=6;
        }
        QtPageBean<Products> list=ps.getShouyeProductsPage(currentPage,pageSize);
        writeValue(list,response);
    }

    protected void getProductsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String c_idStr = request.getParameter("c_id");

        int c_id=0;
        if(c_idStr!=null&&c_idStr.length()>0){
            c_id=Integer.parseInt(c_idStr);
        }
        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){//如果传递的参数不为空，则传入的值作为页面显示的数量
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=6;
        }
       QtPageBean<Products> list=ps.findByQtProductsPage(c_id,currentPage,pageSize);
        writeValue(list,response);
    }

    //前台查看详情
    protected void toDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Products products=ps.findById(id);
        request.setAttribute("products",products);
        request.getRequestDispatcher("/WEB-INF/client/detail.jsp").forward(request,response);
       /* writeValue(products,response);*/
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

        PageBean bean =  ps.findByProductsPage(currentPage,currentCount);
        // 将数据存储到request范围，跳转到product_list.jsp页面展示
        request.setAttribute("bean", bean);
        request.getRequestDispatcher("/WEB-INF/admin/products_list.jsp").forward(request,response); //页面转发
        return;

    }


    //编辑物品信息表单回显
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id= WebUtils.parseInt(request.getParameter("id"),0);
         Products products=ps.findById(id);
         request.setAttribute("products",products);
         List<Category> list=cs.selectAll();
         request.setAttribute("cList" ,list);
         System.out.println(products);
         request.getRequestDispatcher("/WEB-INF/admin/edit_products.jsp").forward(request,response);
    }
    //删除分类信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        ps.deleteProducts(id);
        response.sendRedirect(request.getContextPath()+"/ProductsServlet?action=list"); //重定向防止重复提交哦
    }

    protected void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request,response);
    }


    //模糊查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String name=request.getParameter("name").trim();
        System.out.println(name);
        List<Products> list=ps.findByMap(name);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/admin/products_list.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/admin/list_products.jsp").forward(request, response);
        }
    }

    //模糊查询
    protected void findBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String name=request.getParameter("name").trim();
        System.out.println(name);
        List<Products> list=ps.findByMap(name);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/client/list_search.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/client/list_search.jsp").forward(request, response);
        }
    }

    //跳转到添加或者编辑物品信息的页面
    protected void toEditProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到登陆界面
        List<Category> list=cs.selectAll();
        request.setAttribute("cList" ,list);
        request.getRequestDispatcher("/WEB-INF/admin/add_products.jsp").forward(request,response);
    }

}
