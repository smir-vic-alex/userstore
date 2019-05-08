<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute/>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <title>smm Posting</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.structure.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/timepicker/clockpicker/dist/jquery-clockpicker.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/business.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/timepicker/clockpicker/dist/jquery-clockpicker.js"></script>

    <script type="text/javascript">
        $(function() {

            $('#calendar').datepicker();

        });
    </script>
</head>