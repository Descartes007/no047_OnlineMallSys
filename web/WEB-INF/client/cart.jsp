<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>

<div class="content content-nav-base shopcart-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <div class="inner-cont2">
                    <jsp:include page="/WEB-INF/common/nav.jsp"/>
                </div>
            </div>
        </div>
    </div>

    <div class="cart w1200">
        <br>


        <div class="cart-table-th">
            <div class="th th-chk">
                <div class="select-all">
                    <div class="cart-checkbox">
                        <input class="check-all check" id="allCheckked" type="checkbox" value="true">
                    </div>
                    <label>&nbsp;&nbsp;全选</label>
                </div>
            </div>
            <div class="th th-item">
                <div class="th-inner">
                    商品
                </div>
            </div>
            <div class="th th-price">
                <div class="th-inner">
                    单价
                </div>
            </div>
            <div class="th th-amount">
                <div class="th-inner">
                    数量
                </div>
            </div>
            <div class="th th-sum">
                <div class="th-inner">
                    小计
                </div>
            </div>
            <div class="th th-op">
                <div class="th-inner">
                    操作
                </div>
            </div>
        </div>
        <div class="OrderList">
            <div class="order-content" id="list-cont">
                <div class="crumb">
                    <a href="javascript:getShouye();">首页</a>
                    <span>></span>
                    <a href="javascript:;">购物车列表</a>
                    <br>
                </div>
                <c:forEach items="${list}" var="c">

                    <ul class="item-content layui-clear">

                        <li class="th th-chk">
                            <div class="select-all">
                                <div class="cart-checkbox">
                                    <input class=" check"  type="checkbox" num="all" name="ck" value="${c.id}" >
                                </div>
                            </div>
                        </li>
                        <li class="th th-item">
                            <div class="item-cont">
                                <a href="CommonServlet?action=toDetail&id=${c.pid}"><img src="${pageContext.request.contextPath}/upload/${c.profile}" alt=""></a>
                                <div class="text">
                                    <div class="title">${c.name}</div>
                                    <%--<p><span>粉色</span>  <span>130</span>cm</p>--%>
                                </div>
                            </div>
                        </li>
                        <li class="th th-price">
                            <span class="th-su">${c.price}0￥</span>
                        </li>
                        <li class="th th-amount" >

                            <div class="box-btn layui-clear">
                                <div class="less layui-btn"  <%--onclick="updateCartNum('${c.quantity}','${c.stock}','${c.id}')"--%>>-</div>

                                <input class="Quantity-input"     value="${c.quantity}" disabled="disabled">
                                <input class="Quantity-input2" type="hidden"  value="${c.id}"/>
                                <input class="Quantity-input3" type="hidden"  value="${c.stock}"/>
                                <div class="add layui-btn"  <%--onclick="updateCartNum('${c.quantity}','${c.stock}','${c.id}')"--%>>+</div>
                            </div>
                        </li>
                        <li class="th th-sum">
                            <span class="sum">${c.price*c.quantity}0</span>
                        </li>
                        <li class="th th-op" style="width: 100px;">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" href="ProductsServlet?action=toDetail&id=${c.pid}">返回</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath}/CartServlet?action=delete&id=${c.id}"  >删除</a>
                        </li>
                    </ul>
                </c:forEach>


            </div>
        </div>





        <div class="FloatBarHolder layui-clear">
            <div class="th th-chk">
                <div class="select-all">
                    <div class="cart-checkbox">
                        <input class="check-all check" id="" name="select-all" type="checkbox"  value="true">
                    </div>
                    <label>&nbsp;&nbsp;已选<span class="Selected-pieces">0</span>件</label>
                </div>
            </div>
            <%--<div class="th batch-deletion">
                <span class="batch-dele-btn">批量删除</span>
            </div>--%>
            <div class="th Settlement">
                <button class="layui-btn" onclick="toOrder()">结算</button>
            </div>
            <div class="th total">
                <p>应付：<span class="total_price">0</span></p>

            </div>
        </div>
    </div>
</div>
<div class="footer">
    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf-8">
    function getShouye() {//跳转到首页
        location.href="CommonServlet?action=toIndex";
    }
    function toOrder(){
        var str="";
        $("input[name=ck]:checked").each(function (index,item) {
            if($("input[name=ck]:checked").length-1==index){
                str+=$(this).val();
            }else{
                str+=$(this).val()+",";
            };
            
        })
        location.href="OrderServlet?action=toOrder&ids="+str;


    }




    /*function updateCartNum(count,stock,id){
        stock=parseInt(stock);
        count =parseInt(count);
        if(stock ==1){
            alert("库存只有1了")
        }
        if (count>=stock) {
            alert("已经达到最大库存了");
            count=stock;
        }
        location.href="${pageContext.request.contextPath}/CartServlet?action=updateCartNum&count="+count+"&id="+id;
    }
*/
    layui.config({
        base: '${pageContext.request.contextPath}/res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['mm','jquery','element'],function(){
        var mm = layui.mm,$ = layui.$,element = layui.elementZ;

        var car = {
            init : function(){
                var uls = document.getElementById('list-cont').getElementsByTagName('ul');//每一行
                var checkInputs = document.getElementsByClassName('check'); // 所有勾选框
                var checkAll = document.getElementsByClassName('check-all'); //全选框
                var SelectedPieces = document.getElementsByClassName('Selected-pieces')[0];//总件数
                var piecesTotal = document.getElementsByClassName('total_price')[0];//总价
                var batchdeletion = document.getElementsByClassName('batch-deletion')[0]//批量删除按钮
                //计算
                function getTotal(){
                    var seleted = 0,price = 0;
                    for(var i = 0; i < uls.length;i++){
                        if(uls[i].getElementsByTagName('input')[0].checked){
                            seleted += parseInt(uls[i].getElementsByClassName('Quantity-input')[0].value);
                            price += parseFloat(uls[i].getElementsByClassName('sum')[0].innerHTML);
                        }
                    }
                    SelectedPieces.innerHTML = seleted;
                    piecesTotal.innerHTML = '￥' + price.toFixed(2);
                }

                function fn1(){
                    alert(1)
                }
                // 小计
                function getSubTotal(ul){
                    var unitprice = parseFloat(ul.getElementsByClassName('th-su')[0].innerHTML);
                    var count = parseInt(ul.getElementsByClassName('Quantity-input')[0].value);
                    var SubTotal = parseFloat(unitprice*count)
                    ul.getElementsByClassName('sum')[0].innerHTML = SubTotal.toFixed(2);
                }

                for(var i = 0;i < checkInputs.length;i++){
                    checkInputs[i].onclick = function(){
                        if(this.className === 'check-all check'){
                            for(var j = 0;j < checkInputs.length; j++){
                                checkInputs[j].checked = this.checked;
                            }
                        }
                        if(this.checked == false){
                            for(var k = 0;k < checkAll.length;k++){
                                checkAll[k].checked = false;
                            }
                        }
                        getTotal()
                    }
                }

                for(var i = 0; i < uls.length;i++){
                    uls[i].onclick = function(e){
                        e = e || window.event;
                        var el = e.srcElement;
                        var cls = el.className;
                        var input = this.getElementsByClassName('Quantity-input')[0];
                        var input2 = this.getElementsByClassName('Quantity-input2')[0];
                        var input3 = this.getElementsByClassName('Quantity-input3')[0];
                        var less = this.getElementsByClassName('less')[0];
                        var val = parseInt(input.value);
                        var id = parseInt(input2.value);
                        var stock = parseInt(input3.value);
                        var that = this;

                        switch(cls){
                            case 'add layui-btn':
                                input.value = val + 1;
                                input2.value=id;
                                input3.value=stock;

                                /*更改购物车数量*/
                               /* alert("quantity:"+input.value);
                                alert("id:"+id);
                                alert("stock:"+stock);*/
                                if(input.value>stock){
                                    input.value=stock;
                                    layer.msg("库存只剩"+input.value+"了",function () {3000})
                                }


                                var url="CartServlet?action=updateCartNum&count="+input.value+"&id="+id;
                                $.get(url,function () {});
                                getSubTotal(this)
                                break;
                            case 'less layui-btn':
                                if(val > 1){
                                    input.value = val - 1;
                                    var url="CartServlet?action=updateCartNum&count="+input.value+"&id="+$(".Quantity-input").attr('datasrc');
                                    $.get(url,function () {});
                                }
                                getSubTotal(this)
                                break;
                            case 'dele-btn':

                                break;
                        }
                        getTotal()
                    }
                }
             /*   batchdeletion.onclick = function(){
                    if(SelectedPieces.innerHTML != 0){
                        layer.confirm('你确定要删除吗',{
                            yes:function(index,layero){
                                layer.close(index)
                                for(var i = 0;i < uls.length;i++){
                                    var input = uls[i].getElementsByTagName('input')[0];
                                    if(input.checked){
                                        uls[i].parentNode.removeChild(uls[i]);
                                        i--;
                                    }
                                }
                                getTotal()
                            }

                        })
                    }else{
                        layer.msg('请选择商品')
                    }

                }*/
                checkAll[0].checked = true;
                checkAll[0].onclick();
            }

        }


        car.init();


    });
</script>
</body>
</html>
