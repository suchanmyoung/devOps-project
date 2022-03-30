<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Member</title>
	<script type="text/javascript" src="/resources/smartEditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"></style>
</head>
<body>
<h3 class="text-center">게시글 수정</h3>
	<form action="/board/update" method="post" id="boardContents">
		<input type="hidden" name="boardIdx" value="${boardView.boardIdx}">
		<input type="text" name="title" value="${boardView.title}">
		<input type="textarea" name="contents" id="contents" rows="10" cols="100" value="${boardView.contents}">
	<button type="submit" id="saveButton" class="btn-primary">수정</button>
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
