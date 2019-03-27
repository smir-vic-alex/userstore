<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute/>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="header.jsp"/>
<div class="media">
    <jsp:include page="leftMenu.jsp"/>
    <div class="inline content">
        ${data}
    </div>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
