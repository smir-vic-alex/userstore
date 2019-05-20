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
        <h1>Создать новый пост</h1>
        <div class="content-row">
            <c:if test="${not empty form.vkUser}">
                <html:form action="/private/edit/post" method="post" enctype="multipart/form-data">
                    <div>
                        <h3>Куда постить</h3>
                        <div>
                            <c:choose>
                                <c:when test="${not empty form.vkGroups}">
                                    <c:set var="isOneGroup" value="${form.isOneGroup}"/>
                                    <c:forEach var="group" items="${form.vkGroups}">
                                        <c:choose>
                                            <c:when test="${isOneGroup}">
                                                <div id="group-wrapper-${group.vkId}" class="inline create-post-group-wrapper button" onclick="check('${group.vkId}');">
                                            </c:when>
                                            <c:otherwise>
                                                <div id="group-wrapper-${group.vkId}" class="inline create-post-group-wrapper button" onclick="check('${group.vkId}');">
                                            </c:otherwise>
                                        </c:choose>
                                            <div class="inline">
                                                <img class="vk-group-icon" src="${group.avatarUrl}"/>
                                            </div>
                                            <div class="inline checkbox-group-name">
                                                <div class="vk-group-icon-title inline">
                                                    <label for="vkGroupId-${group.vkId}">
                                                        <a href="https://vk.com/club${group.vkId}"
                                                            onclick="window.open(this.href); return false;"
                                                            onkeypress="window.open(this.href); return false;" title="Перейти к группе">
                                                            <c:out value="${group.name}"/>
                                                        </a>
                                                    </label>
                                                </div>
                                            <c:choose>
                                                <c:when test="${isOneGroup}">
                                                    <input id="vkGroupId-${group.vkId}" type="checkbox" name="vkGroupId" checked value="${group.vkId}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <html:checkbox styleId="vkGroupId-${group.vkId}" property="vkGroupId" value="${group.vkId}"/>
                                                </c:otherwise>
                                            </c:choose>
                                                <div class="check-area">
                                                    <div id="vkGroupId-${group.vkId}-checkMark" style="display: none;">
                                                        <img style="width: 50px; height: 50px;" src="${pageContext.request.contextPath}/resources/img/checkmark.png">
                                                    </div>
                                                    <div id="vkGroupId-${group.vkId}-checkMark-off" >
                                                        <img style="width: 50px; height: 50px;" src="${pageContext.request.contextPath}/resources/img/checkmarkOff.png">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${isOneGroup}">
                                            <script>
                                                $(document).ready(function() {
                                                    var id = '#vkGroupId-${group.vkId}';
                                                    $(id).prop("checked", true);
                                                    $(id + '-checkMark-off').css('display','none');
                                                    $(id + '-checkMark').removeAttr( 'style' );
                                                    $('#group-wrapper-${group.vkId}').css( 'box-shadow', 'inset 5px 5px 15px rgba(122,122,122,0.5)' );
                                                });
                                            </script>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${not empty form.delayedVKPost}">
                                    <c:set var="editPost" value="${form.delayedVKPost}"/>
                                    <input name="taskId" value="${editPost.taskId}" type="hidden"/>
                                    <div id="group-wrapper-${editPost.ownerId}" class="inline create-post-group-wrapper button" style="box-shadow :inset 5px 5px 15px rgba(122,122,122,0.5);">
                                        <div class="inline">
                                            <img class="vk-group-icon" src="${editPost.avatarUrl}"/>
                                        </div>
                                        <div class="inline checkbox-group-name">
                                            <div class="vk-group-icon-title inline">
                                                <label for="vkGroupId-${editPost.ownerId}">
                                                    <a href="https://vk.com/club${editPost.ownerId}"
                                                        onclick="window.open(this.href); return false;"
                                                        onkeypress="window.open(this.href); return false;" title="Перейти к группе">
                                                        <c:out value="${editPost.groupName}"/>
                                                    </a>
                                                </label>
                                            </div>
                                            <input id="vkGroupId-${editPost.ownerId}" type="hidden" name="vkGroupId" value="${editPost.ownerId}"/>
                                            <div class="check-area">
                                                <div id="vkGroupId-${editPost.ownerId}-checkMark" >
                                                    <img style="width: 50px; height: 50px;" src="${pageContext.request.contextPath}/resources/img/checkmark.png">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                            <script>
                                function check(checkBoxId) {
                                    var id = '#vkGroupId-' + checkBoxId;

                                    if ($(id).is(":checked")) {
                                        $(id).prop("checked", false);
                                        $(id + '-checkMark').css('display','none');
                                        $(id + '-checkMark-off').removeAttr( 'style' );
                                        $('#group-wrapper-'+ checkBoxId).css( 'box-shadow', '' );
                                    } else {
                                        $(id).prop("checked", true);
                                        $(id + '-checkMark-off').css('display','none');
                                        $(id + '-checkMark').removeAttr( 'style' );
                                        $('#group-wrapper-'+ checkBoxId).css( 'box-shadow', 'inset 5px 5px 15px rgba(122,122,122,0.5)' );
                                    }
                                };
                            </script>
                        </div>
                    </div>
                    <div>
                        <div class="inline">
                            <h3>Текст</h3>
                            <div>
                                <c:choose>
                                    <c:when test="${not empty form.delayedVKPost}">
                                        <input name="postText" class="create-post-input-text" type="text" value="${form.delayedVKPost.message}">
                                    </c:when>
                                    <c:otherwise>
                                        <input name="postText" class="create-post-input-text" type="text">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="create-post-input-checkbox">
                            <div class="inline">
                                <c:choose>
                                    <c:when test="${not empty form.delayedVKPost}">
                                        <c:choose>
                                            <c:when test="${form.delayedVKPost.fromGroup}">
                                                <div>
                                                    <input id="isFromGroup" name="isFromGroup" type="checkbox" value="true" checked/>
                                                    <label for="isFromGroup"><span></span>От имени группы</label>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>
                                                    <input id="isFromGroup" name="isFromGroup" type="checkbox" value="true"/>
                                                    <label for="isFromGroup"><span></span>От имени группы</label>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <div>
                                            <input id="isFromGroup" name="isFromGroup" type="checkbox" value="true"/>
                                            <label for="isFromGroup"><span></span>От имени группы</label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <%--<div><input name="isCommercial" type="checkbox" value="true">Реклама</div>--%>
                                <%--<div><input name="isAddSign" type="checkbox" value="true">Добавить подпись</div>--%>
                            </div>
                            <div class="inline">
                                <div>
                                    <c:choose>
                                        <c:when test="${not empty form.delayedVKPost}">
                                            <input id="isPlanned" name="isPlanned" type="checkbox" value="true" checked/>
                                            <label for="isPlanned"><span></span>Запланировать</label>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="isPlanned" name="isPlanned" type="checkbox" value="true"/>
                                            <label for="isPlanned"><span></span>Запланировать</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <div class="ui-widget inline">
                                        <label for="calendar">Дата: </label>
                                        <c:choose>
                                            <c:when test="${not empty form.delayedVKPost}">
                                                <input id="calendar" name="calendar" autocomplete="off" value="${cat:getDate(form.delayedVKPost.fireDate)}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input id="calendar" name="calendar" autocomplete="off" disabled/>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>

                                    <div class="input-group clockpicker inline" data-placement="right" data-align="top"
                                         data-autoclose="true">
                                        <label for="time">Время: </label>
                                        <c:choose>
                                            <c:when test="${not empty form.delayedVKPost}">
                                                <input name="time" id="time" type="text" class="form-control" autocomplete="off" value="${cat:getTime(form.delayedVKPost.fireDate)}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input name="time" id="time" type="text" class="form-control" autocomplete="off" disabled/>
                                            </c:otherwise>
                                        </c:choose>

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
                        <div id="wrapper" style="margin-top: 20px;">

                                <input id="fileUpload" name="fileUpload" multiple type="file" class="inputfile"/>
                                <label for="fileUpload" class="label" id="labelFileUpload">Выбрать</label>
                            <div id="image-holder" class="imageHolder" style="display: none;">
                            </div>
                            <div id="upload-result">
                            </div>
                        </div>
                        <script>
                            $(document).ready(function() {
                                    $("#fileUpload").on('change', function() {
                                        //Get count of selected files
                                        var countFiles = $(this)[0].files.length;
                                        var imgPath = $(this)[0].value;
                                        var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
                                        var image_holder = $("#image-holder");
                                        image_holder.empty();
                                        if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
                                            if (typeof(FileReader) != "undefined") {
                                                //loop for each file selected for uploaded.
                                                for (var i = 0; i < countFiles; i++)
                                                {
                                                    $("<img />", {
                                                        "id":"deleteButton",
                                                        "src": '${pageContext.request.contextPath}/resources/img/delete-button.png',
                                                        "class": "delete-button"
                                                    }).appendTo(image_holder);

                                                    var reader = new FileReader();
                                                    reader.onload = function(e) {
                                                        $("<img />", {
                                                            "id":"loadedImg",
                                                            "src": e.target.result,
                                                            "class": "thumb-image"
                                                        }).appendTo(image_holder);
                                                    };
                                                    image_holder.show();
                                                    reader.readAsDataURL($(this)[0].files[i]);
                                                    $('#labelFileUpload').css('display','none');
                                                    $("#image-holder").removeAttr( 'style' );
                                                }
                                            } else {
                                                alert("This browser does not support FileReader.");
                                            }
                                        } else {
                                            alert("Pls select only images");
                                        }
                                    });

                                $("#image-holder").on('click', function() {
                                    $('#fileUpload').val('');
                                    $('#labelFileUpload').removeAttr( 'style' );
                                    $('#image-holder').css('display','none');
                                    $('#deleteButton').remove();
                                    $('#loadedImg').remove();
                                });
                            });
                        </script>
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
                    <input type="hidden" name="array" value="1">
                    <input type="hidden" name="array" value="2">
                    <html:submit styleClass="button">Отправить</html:submit>
                </html:form>
            </c:if>
        </div>
    </tiles:put>
</tiles:insert>
