<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()
            +"://"
            +request.getServerName()
            +":"+
            +request.getServerPort()
            +request.getContextPath()
            +"/";
%>

<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>网上购物系统 </title>
    <link rel="icon" href="favicon.ico" type="image/ico">
    <meta name="author" content="yinqi">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/style.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/bootstrap-datepicker/bootstrap-datepicker3.min.css">

    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css" />
    <!--时间选择插件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
   <script type="text/javascript"  src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/perfect-scrollbar.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Chart.js"></script>
    <!--时间选择插件-->
   <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker/locale/zh-cn.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>


</head>
