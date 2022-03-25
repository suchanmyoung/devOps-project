<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Board View</title>
    <script type="text/javascript" src="/resources/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    <style rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"></style>
</head>
<body>
    ${boardView.title}
    ${boardView.contents}
    ${boardView.hit}
    ${boardView.loginId}
    ${boardView.regDate}
</body>
</html>