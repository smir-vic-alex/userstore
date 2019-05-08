<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cat" uri="http://cat.com/tags"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>

<bean:define id="form" name="RemovePostActionForm"/>

{
    "taskId":"${form.taskId}"
    <c:set var="errorMessage" value="${cat:getErrorMessage()}"/>
    <c:set var="userMessage" value="${cat:getUserMessage()}"/>
    <c:if test="${not empty errorMessage}">
    ,
    "error":"${errorMessage}"
    </c:if>
    <c:if test="${not empty userMessage}">
    ,
    "message":"${userMessage}"
    </c:if>
}
