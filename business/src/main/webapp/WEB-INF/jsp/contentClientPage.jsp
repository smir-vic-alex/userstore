<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<bean:define id="form" name="MainPageActionForm"/>

<c:choose>
    <c:when test="${empty form.vkUser and empty form.telegramChannels and empty form.telegramBots}">
        <h1>Нет подключенных социальных сетей</h1>
        <div class="content-row">
            <div>
                Для начала работы Вам необходимо подключить хотя бы одну социальную сеть
            </div>
            <div class="social-network-row">
                <div class="inline">
                    <a href="${pageContext.request.contextPath}/private/client/addVKProfile.do">
                        <img class="social-network-icon"
                             src="${pageContext.request.contextPath}/resources/img/vkLogo.png" alt="VKontakte"/>
                        <div class="social-network-icon-title">
                            ВКонтакте
                        </div>
                    </a>
                </div>
                <div class="inline">
                    <a href="${pageContext.request.contextPath}/private/client/addTlgmBot.do">
                        <img class="social-network-icon"
                             src="${pageContext.request.contextPath}/resources/img/telegramLogo.png" alt="Telegram"/>
                        <div class="social-network-icon-title">
                            Telegram
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty form.vkUser}">
            <h1>ВКонтакте</h1>
            <c:choose>
                <c:when test="${not empty form.vkGroups}">
                    <c:forEach var="group" items="${form.vkGroups}">
                        <div class="content-row">
                            <div class="inline">
                                <img class="vk-group-icon" src="${group.avatarUrl}"/>
                            </div>
                            <div class="vk-group-icon-title inline">
                                <c:out value="${group.name}"/>
                            </div>
                                <%--<html:hidden property="groupId" value="${group.id}"/>--%>
                            <div class="vk-group-button inline">
                                <a href="${pageContext.request.contextPath}/private/create/post.do">
                                    <button class="button" type="submit">Создать пост</button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="content-row">
                        У Вас неподключены группы для автоматического постинга.
                        <a href="${pageContext.request.contextPath}/private/client/show/vk/groups.do">Нажмите чтобы
                            подключить</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:otherwise>
</c:choose>