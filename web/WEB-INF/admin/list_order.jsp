
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>

<body>


<div class="container-fluid">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-toolbar clearfix">

                </div>
                <a class="btn btn-primary btn-sm m-r-5" href="OrderServlet?action=list">返回主界面</a>
                <span id="msg" style="color:red;"></span>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单编号</th><th>总金额</th><th>交易状态</th><th>收货人</th><th>联系方式</th><th>下单时间</th><th>操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="o" >
                                <tr>

                                    <td>${o.orderno}</td>
                                    <td>${o.money}</td>
                                    <td>
                                            <c:if test="${o.status==0}">
                                                <button class="btn btn-sm btn-danger">未支付</button>
                                            </c:if>
                                            <c:if test="${o.status==1}">
                                                <button class="btn btn-sm btn-success">已支付</button>
                                            </c:if>
                                    </td>
                                    <td>${o.receiver}</td>
                                    <td>${o.phone}</td>

                                    <td>${o.createTime}</td>

                                    <td style="text-align:center;">
                                            <a class="btn btn-info btn-sm" href="OrderServlet?action=query&id=${o.id}" ><i class="mdi mdi-pencil"></i></a>
                                            <a class="btn btn-danger btn-sm" href="OrderServlet?action=delete&id=${o.id}" ><i class="mdi mdi-window-close"></i></a>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>





                    </div>
                </div>
            </div>

        </div>

    </div>
</div>



<script type="text/javascript">
    layui.use(['layer', 'form','jquery'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,jquery=layui.jquery;
    $("a.btn-danger").click(function () {
        return confirm("你确定删除订单编号为["+$(this).parent().parent().find("td:first").text()+"]这条记录吗");
    });
    // 给编辑绑定单击事件
        $(function () {
            $("#search").click(function () {
                var orderno = $("#orderno").val();
                if (orderno == "") {
                    $("span.errorMsg").text("订单编号不许为空");
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