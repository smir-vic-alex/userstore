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
        <c:choose>
            <c:when test="${not empty form.delayedVKPosts}">
                <h1>Планируемые посты:</h1>
                <c:forEach var="post" items="${form.delayedVKPosts}">
                    <div id="row-task-id-${post.taskId}" class="content-row">
                        <div class="inline">
                            <img class="vk-group-icon" src="${post.avatarUrl}"/>
                        </div>
                        <div class="vk-group-icon-block inline">
                            <div style="font-size: 1.5em; color: #2a5885;">
                                <a href="https://vk.com/club${post.ownerId}" onclick="window.open(this.href); return false;" onkeypress="window.open(this.href); return false;"><c:out value="${post.groupName}"/></a>
                            </div>
                            <%--https://vk.com/club ownerId--%>
                            <div class="">
                                Дата публикации:
                                <c:out value="${post.fireDate}"/>
                            </div>
                            <div class="">
                                <c:set var="checked">
                                    <c:choose>
                                        <c:when test="${post.fromGroup}">
                                            checked
                                        </c:when>
                                    </c:choose>
                                </c:set>
                                От имени группы:
                                <input type="checkbox" disabled="true" <c:out value="${checked}"/>>
                            </div>
                        </div>
                        <div class="inline" style="float: right;">
                            <ul class="delayedPostMenu">
                                <li><a href=#><img src="${pageContext.request.contextPath}/resources/img/gearIcon.png" style="width: 40px;"></a>
                                    <ul class="delayedPostSubMenu">
                                        <li><a href="${pageContext.request.contextPath}/private/edit/post.do?groupId=${post.ownerId}&taskId=${post.taskId}">Редактировать</a></li>
                                        <li onclick="removeTask(${post.taskId})"><a href="#">Удалить</a></li>
                                        <%--<a href="${pageContext.request.contextPath}/private/remove/post.do?taskId=${post.taskId}"></a>--%>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <html:hidden property="groupId" value="${post.ownerId}"/>
                        <div style="margin:15px;">
                            <div class="inline">
                                Сообщение:

                            </div>
                            <div class="inline vk-post-msg-area">
                                <c:out value="${post.message}"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <script>
                    function removeTask(taskId){

                        $.ajax({
                            url: '${pageContext.request.contextPath}/private/remove/post.do',
                            type: 'POST',
                            data: 'taskId=' + taskId,
                            dataType: 'json',
                            success: function(jsonData) {

                                if(jsonData.message && jsonData.taskId) {
                                    $('<div class="userMessage">'+jsonData.message+'</div>').insertBefore('#row-task-id-' + jsonData.taskId);
                                    $('#row-task-id-' + jsonData.taskId).remove();
                                }
                                if (jsonData.error && jsonData.taskId) {
                                    $('#row-task-id-' + jsonData.taskId).append('<div class="errorMessage">'+jsonData.error+'</div>');
                                }
                                else if (jsonData.error) {
                                    $('#globalMessage').append('<div class="errorMessage">'+jsonData.error+'</div>');
                                }
                            }
                        });
                    }
                </script>
            </c:when>
            <c:when test="${not empty form.vkUser}">
                <h1>ВКонтакте</h1>
                <h2>У вас нет запланированных постов.</h2>
                <h2>Выберите группу, чтобы создать новые</h2>
                <c:choose>
                    <c:when test="${not empty form.vkGroups}">
                        <c:forEach var="group" items="${form.vkGroups}">
                            <div class="content-row">
                                <div class="inline">
                                    <img class="vk-group-icon" src="${group.avatarUrl}"/>
                                </div>
                                <div class="vk-group-icon-title inline">
                                    <a href="https://vk.com/club${group.vkId}" onclick="window.open(this.href); return false;" onkeypress="window.open(this.href); return false;"><c:out value="${group.name}"/></a>
                                </div>
                                <%--https://vk.com/club--%>
                                    <%--<html:hidden property="groupId" value="${group.id}"/>--%>
                                <div class="vk-group-button inline">
                                    <a href="${pageContext.request.contextPath}/private/edit/post.do">
                                        <button class="button" type="submit">Создать пост</button>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="content-row">
                            У Вас неподключены группы для автоматического постинга.
                            <a href="${pageContext.request.contextPath}/private/client/show/communities.do">Нажмите
                                чтобы
                                подключить</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>

    </c:otherwise>
</c:choose>
