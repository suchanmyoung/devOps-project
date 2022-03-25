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
    <div class="align-content-center">
        <ul class="pagination align-content-center">
            <li><a class="page-link" href="/board/page/1}">1</a></li>
            <li><a class="page-link" href="/board/page/2">2</a></li>
            <li><a class="page-link" href="/board/page/3">3</a></li>
        </ul>
    </div>
</div>

</script>
</body>
</html>
