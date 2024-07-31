<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--头文件--%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<%--顶部最上层--%>
<jsp:include page="/WEB-INF/common/top.jsp"/>
<%--模糊搜索--%>
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
            <div class="form-box" style="height: 740px;top: 16px;">
                <form class="layui-form" action="UserServlet?action=registe" method="post">
                    <legend>用户注册</legend>
                    <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                    <div class="layui-form-item">
                        <div class="layui-inline iphone">
                            <div class="layui-input-inline ">
                                <i class="layui-icon layui-icon-email  iphone-icon"></i>
                                <input type="tel" name="email" id="email" value="${requestScope.email}"  placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-username  iphone-icon"></i>
                                <input type="text" name="zsxm" id="zsxm" value="${requestScope.name}"  placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-password  iphone-icon"></i>
                                <input type="password" name="password" id="password"  placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-password  iphone-icon"></i>
                                <input type="password" name="repass" id="repass"  placeholder="请输入重复密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <input type="radio" name="gender" value="男" title="男" checked>
                                <input type="radio" name="gender" value="女" title="女" >
                            </div>

                        </div>


                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="tel" name="telephone" id="telephone" value="${requestScope.telephone}"  placeholder="请输入手机号" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <textarea name="address" id="address"  placeholder="请输入地址" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-inline veri-code">
                            <div class="layui-input-inline">
                                <input id="verifycode" type="text" style="width: 130px;float: left" name="verifycode" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input" style="width: 90px;">
                                <%--<input type="button" class="layui-btn" id="find"  value="验证码" />--%>
                                <a href="javascript:refreshCode();" style="position: relative;top:10px;left:10px;">
                                    <img src="${pageContext.request.contextPath}/CheckCodeServlet" title="看不清点击刷新" id="vcode"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item login-btn">
                        <div class="layui-input-block">
                            <button class="layui-btn" type="submit" id="registe">立即注册</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer">

    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>

</div>
<script type="text/javascript">
    layui.config({
        base: 'static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){
        var $ = layui.$, form = layui.form;

        //切换验证码
        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
        }
        $(function () {
            // 给注册绑定单击事件
            $("#registe").click(function () {

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

                //1 获取用户名输入框里的内容
               var nameText = $("#zsxm").val();
                if (nameText=="") {
                    //4 提示用户结果
                    $("span.errorMsg").text("真实姓名不允许为空！");
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
                    $("span.errorMsg").text("密码不合法！必须由字母，数字下划线组成，并且长度为4到12位");

                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repassText = $("#repass").val();
                //2 和密码相比较
                if (repassText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }

                //验证手机号
                if(!/^1[3|4|5|7|8][0-9]{9}$/.test($("#telephone").val())){
                    $("span.errorMsg").text("手机号不符合规则");
                    return false;
                }
                var companyText=$("#company").val();
                if(companyText==""){
                    $("span.errorMsg").text("请输入竞拍者所属的个人/公司/组织/群体/集团");
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

            });

        });

    })
</script>

</body>
</html>
