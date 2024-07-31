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

            <div class="item">

                <div class="text">
                    <form action="UserServlet?action=update&type=${type}" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <p class="info-cont">
                            我的信息
                        </p>
                        <p class="info-cont">
                            <input type="text" name="name" value="${user.name}">
                        </p>
                        <p class="info-cont">
                           <select name="gender">
                               <option value="${user.gender}">${user.gender}</option>
                               <option value="男">男</option>
                               <option value="女">女</option>
                           </select>

                        </p>
                        <p class="info-cont">
                            <input type="text" name="password" value="${user.password}">
                        </p>
                        <p class="info-cont">
                            <input type="text" name="email" value="${user.email}">
                        </p>
                        <p class="info-cont">
                            <input type="text" name="telephone" value="${user.telephone}">
                        </p>
                        <p class="info-cont">
                            ${user.registeTime}
                        </p>
                        <p class="info-cont">
                            <button type="submit">确定修改</button>
                        </p>
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

</body>
</html>
