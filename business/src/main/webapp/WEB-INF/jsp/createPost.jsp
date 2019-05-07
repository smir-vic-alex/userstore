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
                                <c:set var="isOneGroup" value="${form.isOneGroup}"/>
                                <c:forEach var="group" items="${form.vkGroups}">
                                    <div class="inline" style="min-width: 200px;">
                                        <img class="vk-group-icon" src="${group.avatarUrl}"/>
                                        <div class="vk-group-icon-title inline">
                                            <a href="https://vk.com/club${group.vkId}" onclick="window.open(this.href); return false;" onkeypress="window.open(this.href); return false;"><c:out value="${group.name}"/></a>
                                        </div>
                                        <c:choose>
                                            <c:when test="${isOneGroup}">
                                                <input type="checkbox" name="vkGroupId" checked value="${group.vkId}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <html:checkbox property="vkGroupId" value="${group.vkId}"/>
                                            </c:otherwise>
                                        </c:choose>
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
                        <div class="create-post-input-checkbox">
                            <div class="inline">
                                <div><input name="isFromGroup" type="checkbox" value="true">От имени группы</div>
                                    <%--<div><input name="isAddSign" type="checkbox" value="true">Добавить подпись</div>--%>
                                <div><input name="isCommercial" type="checkbox" value="true">Реклама</div>
                            </div>
                            <div class="inline">
                                <div><input id="isPlanned" name="isPlanned" type="checkbox" value="true">Запланировать</div>
                                <div>
                                    <div class="ui-widget inline">
                                        <label for="calendar">Дата: </label><input id="calendar" name="calendar" autocomplete="off" disabled/>
                                    </div>

                                    <div class="input-group clockpicker inline" data-placement="right" data-align="top" data-autoclose="true">
                                        <label for="time">Время: </label><input name="time" id="time" type="text" class="form-control" autocomplete="off" disabled/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                    </div>
                                    <script type="text/javascript">
                                        $('.clockpicker').clockpicker();
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <h3>Вложения</h3>
                    </div>
                    <script>
                        $(function () {
                            $("#isPlanned").click(function () {
                                if ($(this).is(":checked")) {
                                    $("#calendar").prop("disabled", false);
                                    $("#time").prop("disabled", false);
                                } else {
                                    $("#calendar").prop("disabled", true);
                                    $("#time").prop("disabled", true);
                                }
                            });
                        });
                    </script>
                    <html:submit>Отправить</html:submit>
                </html:form>
            </c:if>
        </div>
    </tiles:put>
</tiles:insert>
