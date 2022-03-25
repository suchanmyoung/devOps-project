<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>BoardList</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>게시글 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tobdy>
            <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.boardIdx}</td>
                <td><a href="/board/${board.boardIdx}">${board.title}</a></td>
                <td>${board.loginId}</td>
                <td>${board.regDate}</td>
                <td>${board.hit}</td>
            </tr>
            </c:forEach>
        </tobdy>
    </table>
<a class="btn btn-primary float-end" href="/boardForm">글쓰기</a>
    <div>
        <ul class="pagination justify-content-center">
                <c:if test="${pageMaker.prev ne false}">
                <li class="page-link">prev</li>
                </c:if>
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="page-link">${num}</li>
            </c:forEach>
                <c:if test="${pageMaker.next ne false}">
                <li class="page-link">next</li>
                </c:if>
        </ul>
    </div>
</div>
<form method="get">
    <input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
    <input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
</form>
</body>
</html>
