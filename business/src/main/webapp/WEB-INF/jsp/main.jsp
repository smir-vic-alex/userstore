<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cat" uri="http://cat.com/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute/>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="header.jsp"/>
<div class="media">
    <c:set var="errorMessage" value="${cat:getErrorMessage()}"/>
    <c:set var="userMessage" value="${cat:getUserMessage()}"/>
    <div id="globalMessage">
    <c:if test="${not empty errorMessage}">
        <div class="errorMessage">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty userMessage}">
        <div class="userMessage">${userMessage}</div>
    </c:if>
    </div>
    <jsp:include page="leftMenu.jsp"/>
    <div class="inline content">
        ${data}
    </div>
    <%--<jsp:include page="footer.jsp"/>--%>
</div>

</body>
</html>
