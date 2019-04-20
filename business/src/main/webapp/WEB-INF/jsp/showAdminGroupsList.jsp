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
        <bean:define id="form" name="ShowClientCommunitiesActionForm"/>
        <h1>Ваши администрируемые сообщества</h1>
        <c:choose>
            <c:when test="${not empty form.vkGroups}">
                Ваши подключенные группы ВКонтакте:
                <c:forEach var="group" items="${form.vkGroups}">
                    <div class="content-row">
                        <html:form action="/private/client/addVKGroup">
                            <div class="inline">
                                <img class="vk-group-icon" src="${group.avatarUrl}"/>
                            </div>
                            <div class="vk-group-icon-title inline">
                                <c:out value="${group.name}"/>
                            </div>
                            <html:hidden property="groupId" value="${group.id}"/>
                        </html:form>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${not empty form.notConnectedVkGroups}">
                У Вас нет подключенных групп ВКонтакте. Пожалуйста выберите и добавьте из списка:
                <c:forEach var="group" items="${form.notConnectedVkGroups}">
                    <div class="content-row">
                        <html:form action="/private/client/addVKGroup">
                            <div class="inline">
                                <img class="vk-group-icon" src="${group.avatarUrl}"/>
                            </div>
                            <div class="vk-group-icon-title inline">
                                <c:out value="${group.name}"/>
                            </div>
                            <html:hidden property="groupId" value="${group.id}"/>
                            <div class="vk-group-button inline">
                                <html:submit value="Добавить группу" styleClass="button"/>
                            </div>
                        </html:form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                У Вас нет групп для администрирования
            </c:otherwise>
        </c:choose>
    </tiles:put>
</tiles:insert>