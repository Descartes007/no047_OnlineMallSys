<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.school.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<div class="site-nav-bg">
    <div class="site-nav w1200">
        <p class="sn-back-home">
            <i class="layui-icon layui-icon-home"></i>
            <a href="CommonServlet?action=toIndex">首页</a>
        </p>
        <div class="sn-quick-menu">

           <%
               User user = (User) request.getSession().getAttribute("user");
            if(null == user){
            %>
             |<div class="login"><a href="UserServlet?action=toRegiste">注册</a></div>
              | <div class="login"><a href="UserServlet?action=toLogin">用户登录</a></div>
            <%
                }else{
            %>
              | <div class="login">欢迎您： ${user.name}</div>
               <div class="login"><a href="${pageContext.request.contextPath}/CommonServlet?action=loginOut" onclick="javascript:return confirm_logout()">退出</a> </div>
            <div class="login"><a href="${pageContext.request.contextPath}/UserServlet?action=MyInfo" >我的信息</a> </div>
            <div class="login"><a href="${pageContext.request.contextPath}/OrderServlet?action=findMyOrder" >我的订单</a> </div>

            <%
                }
            %>


            <div class="sp-cart"><a href="CartServlet?action=list">购物车</a><span id="gwctotal"></span></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    //退出确认框
    function confirm_logout() {
        var msg = "您确定要退出登录吗？";
        if (confirm(msg)==true){
            return true;
        }else{
            return false;
        }
    }

</script>
</body>
</html>
