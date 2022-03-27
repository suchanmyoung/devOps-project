<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Member</title>
	<script type="text/javascript" src="/resources/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"></style>
</head>
<body>
<h3>게시판</h3>
	<form action="/boardForm" method="post" id="boardContents">
		<input type="text" name="title" value="게시글 제목">
		<input type="textarea" name="contents" id="contents" rows="10" cols="100">
	<button type="submit" id="saveButton" class="btn-primary">게시글 작성</button>
	</form>

<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "contents",
		sSkinURI: "/resources/smartEditor/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
	});

	$("#saveButton").on("click", function(){
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
		if(confirm()){
			$("#boardContents").submit();
		}
	})

</script>

</body>
</html>