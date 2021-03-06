<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<script type="text/javascript" src="/resources/smartEditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/smartEditor/sample/js/plugin/hp_SE2M_AttachQuickPhoto.js"></script>
</head>
<body>
<h3 class="text-center">게시글 작성</h3>
	<form action="/boardForm" method="post" id="boardContents">
		<input type="text" name="title" value="게시글 제목">
		<input type="textarea" name="contents" id="contents" rows="25" cols="200">
	<button type="submit" id="saveButton" class="btn-primary">게시글 작성</button>
	</form>
<button class="btn btn-success" onclick="location.href='/board'">게시판으로</button>
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
