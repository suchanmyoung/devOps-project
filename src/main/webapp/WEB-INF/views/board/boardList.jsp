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
                <td>${board.boardIdx}</td>
                <td>${board.title}</td>
                <td>${board.loginId}</td>
                <td>${board.regDate}</td>
                <td>${board.hit}</td>
            </c:forEach>
        </tobdy>
    </table>
<a class="btn btn-primary float-end" href="/boardForm">글쓰기</a>
    <div class="align-content-center">
        <ul class="pagination">
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
        </ul>
    </div>
</div>

</script>
</body>
</html>
