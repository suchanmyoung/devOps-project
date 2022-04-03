<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>BoardList</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<style>
    .active{
        background-color: #cdd5ec;
    }
</style>
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
    <button class="btn btn-success" onclick="location.href='/'">홈페이지</button>
<a class="btn btn-primary float-end" href="/boardForm">글쓰기</a>
    <div>
        <ul class="pagination justify-content-center pageInfo">
                <c:if test="${pageMaker.prev ne false}">
                    <li class="page-link"><a href="${pageMaker.startPage-1}">prev</a></li>
                </c:if>
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="page-link ${pageMaker.criteria.pageNum == num ? "active":""}"><a href="${num}">${num}</a></li>
            </c:forEach>
                <c:if test="${pageMaker.next ne false}">
                    <li class="page-link"><a href="${pageMaker.endPage +1}">next</a></li>
                </c:if>
        </ul>
    </div>
    <form id="moveForm" method="get">
        <input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
    </form>
</div>

<script>
    let moveForm = $("#moveForm");

    $(".pageInfo a").on("click", function(e){
        e.preventDefault();
        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
        moveForm.attr("action", "/board");
        moveForm.submit();
    });

</script>
</body>
</html>
