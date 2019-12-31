<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="/jstl" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}"/>
<c:set var="jsPath" scope="request" value="${ctx}/js"/>
<c:set var="cssPath" scope="request" value="${ctx}/css"/>
<c:set var="imgPath" scope="request" value="${ctx}/img"/>
<c:set var="_permission" scope="request" value="${sessionScope._sessionUserPermission.noPermissions}"/>