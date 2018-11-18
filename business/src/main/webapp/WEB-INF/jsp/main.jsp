<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute/>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"/>

<body style="margin: 0;">

<div class="line"></div>
<div class="top-menu-container">
    <div style="max-width: 1000px; margin: auto ">
        <jsp:include page="topMenuIndex.jsp"/>
    </div>
</div>
<div style="position: relative; min-height: 100%; ">
    <div style="padding-bottom: 100%; max-width: 1000px; margin: auto">
        ${data}
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
