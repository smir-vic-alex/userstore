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
        <h1>Администрируемые сообщества</h1>
        <c:choose>
            <c:when test="${not empty form.vkGroups or not empty form.notConnectedVkGroups}">
                <c:if test="${not empty form.vkGroups}">
                    <c:forEach var="group" items="${form.vkGroups}">
                        <div class="content-row">
                            <div class="inline">
                                <img class="vk-group-icon" src="${group.avatarUrl}"/>
                            </div>
                            <div class="vk-group-icon-title inline">
                                <c:out value="${group.name}"/>
                            </div>
                            <html:hidden property="groupId" value="${group.id}"/>
                            <div class="vk-group-button inline">
                                <a href="${pageContext.request.contextPath}/private/edit/post.do">
                                    <button class="button" type="submit">Создать пост</button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty form.notConnectedVkGroups}">
                    <c:choose>
                        <c:when test="${empty form.vkGroups}">
                            <h3>У Вас нет подключенных групп ВКонтакте.</h3>
                            <h3> Пожалуйста выберите и добавьте из списка:</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>Неподключенные группы:</h3>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="group" items="${form.notConnectedVkGroups}">
                        <div class="content-row">
                            <html:form action="/private/client/addVKGroup">
                                <div class="inline">
                                    <img class="vk-group-icon" src="${group.avatarUrl}"/>
                                </div>
                                <div class="vk-group-icon-title inline">
                                    <c:out value="${group.name}"/>
                                </div>
                                <html:hidden property="groupId" value="${group.vkId}"/>
                                <div class="vk-group-button inline">
                                    <html:submit value="Добавить группу" styleClass="button"/>
                                </div>
                            </html:form>
                        </div>
                    </c:forEach>
                </c:if>
            </c:when>
            <c:otherwise>
                У Вас нет групп для администрирования
            </c:otherwise>
        </c:choose>
    </tiles:put>
</tiles:insert>