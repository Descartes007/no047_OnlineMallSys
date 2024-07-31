package edu.school.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action=request.getParameter("action");
        try {
            Method method=this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);//通过反射来调用业务，优化ifelse
            method.invoke(this,request,response);//调用目标业务，方法

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    //将传入的对象直接进行序列化json,并返回客户端
    public void writeValue(Object obj, HttpServletResponse response) throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }
    //将传入的对象进行序列化json,并返回
    public String writeValueAsString(Object obj) throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
