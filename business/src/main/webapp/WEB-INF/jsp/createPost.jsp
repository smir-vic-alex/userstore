<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cat" uri="http://cat.com/tags" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<tiles:insert definition="main" flush="false">
    <tiles:put name="topMenu" type="string">
        <jsp:include page="topMenuClientPage.jsp"/>
    </tiles:put>
    <tiles:put name="data" type="string">
        <bean:define id="form" name="CreatePostActionForm"/>

        <c:forEach var="network" items="${form.availableNetworks}">
            <html:form action="/private/send/post">
                <c:out value="${network.vkUserId}"/>
                <html:hidden property="groupId" value="${network.vkUserId}"/>
                <html:hidden property="typeNetwork" value="${network.type}"/>
                <html:textarea property="message"/>
                <html:text property="time"/>
                <html:submit value="Разместить пост"/>
            </html:form>
        </c:forEach>
    </tiles:put>
</tiles:insert>