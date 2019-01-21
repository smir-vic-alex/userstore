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
        <bean:define id="form" name="MakePostInChannelActionForm"/>

        <c:forEach var="channel" items="${form.channels}">
            <html:form action="/private/create/post/tlgm">
                Название канала <html:text property="channelName" value="${channel.name}"/>
                ID бота <c:out value="${channel.userTelegramId}"/>
                Сообщение <html:textarea property="message"/>
                <html:submit value="Разместить пост"/>
            </html:form>
        </c:forEach>
    </tiles:put>
</tiles:insert>