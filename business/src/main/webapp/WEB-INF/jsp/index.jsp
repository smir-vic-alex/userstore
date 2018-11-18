<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<tiles:insert definition="main" flush="false">
    <tiles:put name="topMenu" type="string">
        <jsp:include page="topMenuIndex.jsp"/>
    </tiles:put>
    <tiles:put name="data" type="string">
        <jsp:include page="contentIndexPage.jsp"/>
    </tiles:put>
</tiles:insert>