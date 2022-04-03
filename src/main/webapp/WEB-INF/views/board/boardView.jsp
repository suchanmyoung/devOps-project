<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Board View</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="text-center text-primary">
        현재 서버 포트는 : <%=request.getLocalPort()%>
        <h2>제목 : ${boardView.title}</h2>
        <h3>내용 ${boardView.contents}</h3>
        <h3>조회수 : ${boardView.hit}</h3>
        <h3>작성자 : ${boardView.loginId}</h3>
        <h3>작성일자 : ${boardView.regDate}</h3>

    <c:choose>
        <c:when test="${not empty loginMember}">
        <div id="button" class="text-center">
            <form action="/board/update/${boardView.boardIdx}" method="get">
                <input type="hidden" name="loginId" value="${loginMember.loginId}">
                <button type="submit" class="btn btn-success">수정</button>
            </form>
            <form action="/board/delete" method="post">
                <input type="hidden" name="boardIdx" value="${boardView.boardIdx}">
                <button type="submit" class="btn btn-danger">삭제</button>
            </form>
        </div>
        </c:when>
        <c:when test="${not empty naverMember}">
            <div id="button" class="text-center">
                <form action="/board/update/${boardView.boardIdx}" method="get">
                    <input type="hidden" name="loginId" value="${naverMember}">
                    <button type="submit" class="btn btn-success">수정</button>
                </form>
                <form action="/board/delete" method="post">
                    <input type="hidden" name="boardIdx" value="${boardView.boardIdx}">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </form>
            </div>
        </c:when>
    </c:choose>
    </div>
    <button class="btn btn-success" onclick="location.href='/boardList.jsp'">게시판으로</button>
</div>
</body>
</html>