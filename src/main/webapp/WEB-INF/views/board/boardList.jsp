<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Member</title>
</head>
<body>

<script>
	<c:if test="${not empty accessDenied}">
		accessDeniedAlert(${accessDenied}, ${redirectUrl});
	</c:if>

</script>

<div class="register">
	<h3>게시판</h3>

</div>
</body>
</html>
