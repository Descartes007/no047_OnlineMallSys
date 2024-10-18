<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<%--顶部最上层--%>
<jsp:include page="/WEB-INF/common/top.jsp"/>
<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>


<div class="content content-nav-base  login-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <%--导航栏--%>
                <jsp:include page="/WEB-INF/common/nav.jsp"/>
            </div>
        </div>
    </div>
    <div class="login-bg">
        <div class="login-cont w1200">
            <div class="form-box">
                <form class="layui-form" action="UserServlet?action=login" method="post">
                    <legend>用户登录</legend>
                    <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                    <div class="layui-form-item">
                        <div class="layui-inline ">
                            <div class="layui-input-inline">
                                <input type="text" name="email" id="email" value="${cookie.email.value }"  placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <br>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input id="password" type="password" name="password"   value="${cookie.password.value }" placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <input type="checkbox" name="che" value="yes" lay-skin="primary" title="三天免登录" >
                            &emsp; &emsp;


                        </div>
                    </div>
                    <div class="layui-form-item login-btn">
                        <div class="layui-input-block">
                            <button class="layui-btn" type="submit" id="login">立即登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer">

    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script type="text/javascript">
    layui.config({
        base: 'static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){
        var $ = layui.$,form = layui.form;
        $(function () {
            // 给注册绑定单击事件
            $("#login").click(function () {

                // 邮箱验证：xxxxx@xxx.com
                //1 获取邮箱里的内容
                var emailText = $("#email").val();
                //2 创建正则表达式对象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用户
                    $("span.errorMsg").text("邮箱格式不合法！");

                    return false;
                }


                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{4,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不允许为空");
                    return false;
                }


                // 去掉错误信息
                $("span.errorMsg").text("");

            });

        });


    })
</script>

</body>
</html>