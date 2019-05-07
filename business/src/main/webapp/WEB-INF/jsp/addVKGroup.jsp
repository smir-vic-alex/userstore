<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cat" uri="http://cat.com/tags" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<bean:define id="form" name="ClientAddVKGroupActionForm"/>

<tiles:insert definition="main" flush="false">
    <tiles:put name="topMenu" type="string">
        <jsp:include page="topMenuClientPage.jsp"/>
    </tiles:put>
    <tiles:put name="data" type="string">
        <h1>Добавить группу</h1>
        <h2>Из-за правил вконтакте необходимо </h2>
        <h2>вручную предоставить специальный код авторизации</h2>
        <div class="content-row">
            <html:form action="/private/redirect/vk/group/uri">
                <input name="code" type="text">
                <html:submit>Отправить</html:submit>
            </html:form>
        </div>
        <div>
            <a href="${form.userActorAuthUrl}" onclick="window.open(this.href); return false;" onkeypress="window.open(this.href); return false;">Открыть страницу авторизации ВКонтакте</a>
        </div>
    </tiles:put>
</tiles:insert>