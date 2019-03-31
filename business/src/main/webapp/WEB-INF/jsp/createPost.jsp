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
            <div>
                <h3>Куда постить</h3>
                <div>вк телега</div>
            </div>
            <div>
                <div class="inline">
                    <h3>Текст</h3>
                    <div>
                        <input class="create-post-input-text" type="text">
                    </div>
                </div>
                <div class="inline create-post-input-checkbox">
                    <div><input type="checkbox">Запланировать</div>
                    <div><input type="checkbox">От имени группы</div>
                    <div><input type="checkbox">Добавить подпись</div>
                    <div><input type="checkbox">Реклама</div>
                </div>
            </div>
            <div>
                <h3>Вложения</h3>
            </div>
        </div>
    </tiles:put>
</tiles:insert>
