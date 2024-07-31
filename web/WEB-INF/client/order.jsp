<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>


<div class="content content-nav-base information-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <div class="inner-cont2">
                    <jsp:include page="/WEB-INF/common/nav.jsp"/>
                </div>
            </div>
        </div>

    </div>

    <div class="info-list-box">

        <div class="info-list w1200">
            <div class="item-box layui-clear" id="list-cont">
                <div class="crumb" style="padding-left: 0px;">
                    <a href="javascript:getShouye();">首页</a>
                    <span>></span>
                    <a href="CartServlet?action=list">返回购物车</a>
                    <span>></span>
                    <a href="javascript:;">购物清单结算</a>
                </div>
                <br>
        <c:forEach items="${list}" var="o">
                <div class="item" style="width: 150px;">
                    <div>
                        <img  style="width:100px;height:100px;"  src="${pageContext.request.contextPath}/upload/${o.profile}" alt="">
                    </div>
                    <div class="text">
                        <p class="data">${o.name}</p>
                        <p class="data">${o.price}0￥</p>
                        <p class="data">数&emsp;量：${o.quantity}</p>
                    </div>
                </div>
        </c:forEach>

            </div>
            <div id="demo0" style="text-align: center;">
                <div class="item"  style="height: 500px;">
                    <div>
                        订单编号：${orderno}
                    </div>
                    <div class="text">

                        <p>合计：<span style="color:red"><b>${totalPrice}</b>￥</span></p>

                       <P></P>
                        <form class="layui-form" style="line-height: 40px;" action="OrderServlet?action=add" method="post">
                            <h3>编辑收货地址</h3>
                            <input type="hidden" name="totalPrice" value="${totalPrice}">
                            <input type="hidden" name="orderno" value="${orderno}">


                            <div class="layui-inline iphone">
                                <div class="layui-input-inline ">
                                     <input type="text" name="receiver" id="receiver" value="${sessionScope.name}"  placeholder="请输入收货人姓名" autocomplete="off" class="layui-input">
                                </div>
                                <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                            </div><br>
                            <div class="layui-inline iphone">
                                <div class="layui-input-inline ">
                                        <input type="text" name="phone" id="phone" value="${sessionScope.phone}"  placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                </div>
                            </div><br>
                            <div class="layui-inline iphone">
                                <div class="layui-input-inline ">
                                        <textarea name="address" id="address"  placeholder="请输入地址" class="layui-textarea">${address}</textarea>
                                </div>
                            </div>
                            <br>
                            <div class="layui-inline iphone">
                                <div class="layui-input-inline" >
                                    <img  src="${pageContext.request.contextPath}/res/static/img/wxzf.png"/>

<br>

                                    <img src="${pageContext.request.contextPath}/res/static/img/zfb.png"/>
                                </div>
                            </div>




                            <div class="layui-form-item">

                                    <button class="layui-btn layui-btn-danger" type="submit" id="pay">立即支付</button>

                            </div>
                        </form>
                    </div>
                </div>

        </div>
    </div>
</div>

</div>
<div class="footer">
    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script type="text/javascript">
    $(function () {
        // 给注册绑定单击事件
        $("#pay").click(function () {
            var nameText = $("#receiver").val();
            if (nameText=="") {
                //4 提示用户结果
                $("span.errorMsg").text("收货人姓名不允许为空！");
                return false;
            }

            //验证手机号
            if(!/^1[3|4|5|7|8][0-9]{9}$/.test($("#phone").val())){
                $("span.errorMsg").text("手机号不符合规则");
                return false;
            }
            var addressText=$("#address").val();
            if(addressText==""){
                $("span.errorMsg").text("收货地址不允许为空");
                return false;
            }



            // 去掉错误信息
            $("span.errorMsg").text("");

        });

    });
    function shopAdd(id,url){
        var count = $('.number-cont input').val();
        alert(count);
        location.href="CartServlet?action=addCart&id="+id+"&count="+count+"&url="+url;
    }

    function getShouye() {
            location.href="CommonServlet?action=toIndex";
        }

</script>
</body>
</html>
