<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Member</title>
	<script type="text/javascript" src="/resources/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<style rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"></style>
</head>
<body>
<h3>게시판</h3>
	<form action="/board" method="post">
		<input type="text" name="title" value="게시글 제목">
		<input type="textarea" name="contents" id="ir1" rows="10" cols="100">
	<button type="submit" class="btn-primary">게시글 작성</button>
	</form>

<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "ir1",
		sSkinURI: "/resources/smartEditor/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
	});
</script>
</body>
</html>
