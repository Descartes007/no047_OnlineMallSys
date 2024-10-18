<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>
<div class="row lyear-wrapper">
    <div class="lyear-login">
        <div class="login-center">
            <div class="login-header text-center">
                <a href="#"> <h4  style="color: #15c377;">后台系统登录</h4></a>
            </div>
            <form action="AdminServlet?action=login" method="post" >
                <div class="form-group has-feedback feedback-left">
                    <input type="text" placeholder="请输入邮箱" class="form-control" name="email" id="email" />
                    <span class="mdi mdi-account form-control-feedback" aria-hidden="true"> </span>
                    <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                </div>
                <div class="form-group has-feedback feedback-left">
                    <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" />
                    <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
                </div>
                <div class="form-group has-feedback feedback-left row">
                    <div class="col-xs-7">
                        <input type="text" name="verifycode" id="verifycode" class="form-control" placeholder="验证码">
                        <span class="mdi mdi-check-all form-control-feedback" aria-hidden="true"></span>
                    </div>
                    <div class="col-xs-5">
                        <img src="${pageContext.request.contextPath}/CheckCodeServlet" class="pull-right" id="vcode" style="cursor: pointer;" onclick="this.src=this.src+'?d='+Math.random();" title="点击刷新" alt="captcha">
                    </div>
                </div>

                <div class="form-group">
                    <button class="btn btn-block btn-primary" type="submit" id="login"  >立即登录</button>

                </div>
            </form>
            <hr>
            <footer class="col-sm-12 text-center">
            </footer>
        </div>
    </div>
</div>

<script type="text/javascript">
    function refreshCode(){
        //1.获取验证码图片对象
        var vcode = document.getElementById("vcode");
        //2.设置其src属性，加时间戳
        vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
    }
    layui.use(['layer', 'form','jquery'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,jquery=layui.jquery;
        $("#login").on("click", function() {

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

            //1 获取密码输入框里的内容
            var password = $("#password").val();
            if (password=="") {
                //4 提示用户结果
                $("span.errorMsg").text("密码不允许为空！");
                return false;
            }




            // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
            var codeText = $("#verifycode").val();

            //去掉验证码前后空格
            // alert("去空格前：["+codeText+"]")
            codeText = $.trim(codeText);
            // alert("去空格后：["+codeText+"]")

            if (codeText == null || codeText == "") {
                //4 提示用户
                $("span.errorMsg").text("验证码不能为空！");

                return false;
            }

            // 去掉错误信息
            $("span.errorMsg").text("");


        })



    });
</script>

</body>


</html>
