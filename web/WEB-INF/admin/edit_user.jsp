<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    用户信息
                    <hr>
                    <form method="post" action="UserServlet"  method="post" class="site-form">
                        <input type="hidden" value="${empty param.id ?"add":"update"}" name="action"/>
                        <input type="hidden" value="${user.id}" name="id">
                        <input type="hidden" value="2" name="type">
                        <div class="form-group">
                            <label for="name">真实姓名：</label>
                            <input class="form-control" id="name" name="name" value="${user.name}" type="text" placeholder="请输入姓名"></input>
                            <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                        </div>

                        <div class="form-group">
                            <label for="name">密&emsp;&emsp;码：</label>
                            <input class="form-control" id="password" name="password" value="${user.password}" type="text" placeholder="请输入密码"></input>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-12">性别：</label>
                            <div class="col-xs-12">
                                <label class="radio-inline" for="gender1">
                                    <input type="radio" id="gender1"  name="gender" value="男" checked>
                                    男:
                                </label>
                                <label class="radio-inline" for="gender">
                                    <input type="radio" id="gender" name="gender" value="女">
                                    女：
                                </label>

                            </div>
                        </div>

                        <div class="form-group">
                            <label for="telephone">手机号：</label>
                            <input class="form-control" id="telephone" name="telephone" value="${user.telephone}" type="text" placeholder="请输入手机号"></input>
                        </div>

                        <div class="form-group">
                            <label for="email">邮&emsp;&emsp;箱：</label>
                            <input class="form-control" id="email" name="email" value="${user.email}" type="text" placeholder="请输入邮箱"></input>
                        </div>

                        <div class="form-group">
                            <label for="address">住址：</label>
                            <textarea class="form-control" id="address" name="address" rows="6" value="" placeholder="请输入详细住址">${user.address}</textarea>
                        </div>




                        <button type="submit" class="btn btn-primary" id="edit">${empty param.id ?"确认添加":"确认修改"}</button>
                        <a href="CategoryServlet?action=list" class="btn btn-info">取消操作</a>
                    </form>

                </div>
            </div>
        </div>

    </div>



</div>





</body>

<script type="text/javascript">
layui.use(['layer', 'form','jquery'], function(){
    var layer = layui.layer
    ,form = layui.form
    ,jquery=layui.jquery;
            // 给注册绑定单击事件
            $("#edit").click(function () {
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
                var nameText = $("#name").val();
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



                //验证手机号
                if(!/^1[3|4|5|7|8][0-9]{9}$/.test($("#telephone").val())){
                    $("span.errorMsg").text("手机号不符合规则");
                    return false;
                }
                var address=$("#address").val();
                if(address==""){
                    $("span.errorMsg").text("请输入住址");
                    return false;
                }




                // 去掉错误信息
                $("span.errorMsg").text("");

            });





    })

</script>
</body>

</html>