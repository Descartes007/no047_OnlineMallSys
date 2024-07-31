<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    编辑订单信息
                    <hr>
                    <form action="OrderServlet?action=update"   method="post" class="site-form">

                        <input type="hidden" value="${order.id}" name="id">


                        <div class="form-group">
                            <label for="orderno">订单编号：</label>
                            <input class="form-control" id="orderno" readonly name="orderno" value="${order.orderno}" type="text" placeholder="请输入订单编号"></input>
                            <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                        </div>
                        <div class="form-group">
                            <label for="money">总金额：</label>
                       <input class="form-control" id="money" readonly name="money" value="${order.money}" type="text" placeholder="请输入总金额"></input>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-12" for="status">交易状态</label>
                            <div class="col-xs-12">
                                <select class="form-control" id="status" name="status" size="1">
                                    <option value="${order.status}"><c:if test="${order.status==1}">已支付</c:if><c:if test="${order.status==0}">未支付</c:if> </option>
                                    <option value="0">未支付</option>
                                    <option value="1">已支付</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="receiver">收货人 ：</label>
                            <input class="form-control" id="receiver" name="receiver" value="${order.receiver}" type="text" placeholder="请输入收货人姓名"></input>
                        </div>



                        <div class="form-group">
                            <label for="phone">收货人电话 ：</label>
                            <input class="form-control" id="phone" name="phone" value="${order.phone}" type="text" placeholder="请输入收货人电话"></input>
                        </div>

                        <div class="form-group">
                            <label for="description">收货人地址：</label>
                            <textarea class="form-control" id="description" name="description" rows="6" value="" placeholder="请输入收货人地址">${order.address}</textarea>
                        </div>





                        <button type="submit" class="btn btn-primary" id="edit">确认修改</button>
                        <a href="OrderServlet?action=list" class="btn btn-info">取消操作</a>
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
                var name = $("#name").val();
                var address = $("#price").val();
                var phone = $("#phone").val();
                if (name=="") {
                    $("span.errorMsg").text("收货人姓名不许为空");
                    return false;
                }
                if (address=="") {
                    $("span.errorMsg").text("收货地址不许为空");
                    return false;
                }
                if (phone=="") {
                    $("span.errorMsg").text("手机号不许为空");
                    return false;
                }
                // 去掉错误信息
                $("span.errorMsg").text("");
            });






})

</script>
</body>

</html>