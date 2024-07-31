<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<script>
    var interval;
    window.onload = function() {
        interval = window.setInterval("changeSecond()", 1000);
    };
    function changeSecond() {
        var second = document.getElementById("second");
        var svalue = second.innerHTML;
        svalue = svalue - 1;
        if (svalue == 0) {
            window.clearInterval(interval);
            // 下列两行代码用于获取项目名，例如：bookstore
            var pathName = window.location.pathname.substring(1);
            var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));

            location.href = window.location.protocol + '//' + window.location.host + '/'+ webName + '/CommonServlet?action=toIndex';
            return;
        }
        second.innerHTML = svalue;
    }
</script>
<body>
<div id="divcontent">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="60%" border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <
                        <td style="padding-top:30px">
                            <font style="font-weight:bold; color:#FF0000">修改成功！</font><br />
                            <br />
                            <a href="${pageContext.request.contextPath }/CommonServlet?action=toIndex">
                                <span id="second">5</span>秒后自动为您转跳回首页
                            </a>
                        </td>
                    </tr>
                </table>
                <h1>&nbsp;</h1></td>
        </tr>
    </table>
</div>
</body>
</html>
