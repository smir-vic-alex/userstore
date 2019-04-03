<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cat" uri="http://cat.com/tags" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<bean:define id="form" name="CreatePostActionForm"/>

<tiles:insert definition="main" flush="false">
    <tiles:put name="topMenu" type="string">
        <jsp:include page="topMenuClientPage.jsp"/>
    </tiles:put>
    <tiles:put name="data" type="string">
        <h1>Создать пост</h1>
        <div class="content-row">
            <c:if test="${not empty form.vkUser}">
                <html:form action="/private/send/post">
                    <div>
                        <h3>Куда постить</h3>
                        <div>
                            <c:if test="${not empty form.vkGroups}">
                                <c:forEach var="group" items="${form.vkGroups}">
                                    <div class="inline">
                                        <img class="vk-group-icon" src="${group.avatarUrl}"/>
                                        <html:checkbox property="vkGroupId" value="${group.vkId}"/>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div>
                        <div class="inline">
                            <h3>Текст</h3>
                            <div>
                                <input name="postText" class="create-post-input-text" type="text">
                            </div>
                        </div>
                        <div class="inline create-post-input-checkbox">
                            <div><input name="isPlanned" type="checkbox" value="true">Запланировать</div>
                            <div><input name="isFromGroup" type="checkbox" value="true">От имени группы</div>
                            <div><input name="isAddSign" type="checkbox" value="true">Добавить подпись</div>
                            <div><input name="isCommercial" type="checkbox" value="true">Реклама</div>
                            <input type="text" name="calendar" value="" size="10" onClick="xCal(this)" onmouseenter="xCal(this)" onKeyUp="xCal()">
                            <input type="text" name="time" data-toggle="timepicker">
                            <script>
                                document.addEventListener("DOMContentLoaded", function(event)
                                {
                                    timepicker.load({
                                        interval: 15,
                                        defaultHour: 8
                                    });
                                });
                            </script>
                        </div>
                    </div>
                    <div>
                        <h3>Вложения</h3>
                    </div>
                    <html:submit>Отправить</html:submit>
                </html:form>
            </c:if>
        </div>
    </tiles:put>
</tiles:insert>
