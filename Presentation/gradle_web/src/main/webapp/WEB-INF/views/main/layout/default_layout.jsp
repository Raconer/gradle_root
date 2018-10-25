<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-10-23
  Time: 오후 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>taphold demo</title>
        <link rel="stylesheet" href="//code.jquery.com/mobile/1.5.0-alpha.1/jquery.mobile-1.5.0-alpha.1.min.css">
        <script src="<c:url value="/resource/js/lib/jquery-3.3.1.min.js"/>"></script>
        <script src="//code.jquery.com/mobile/1.5.0-alpha.1/jquery.mobile-1.5.0-alpha.1.min.js"></script>
        <link rel="stylesheet" href="<c:url value="/resource/css/contextmenu.css" />" type="text/css">

</head>
<body>
<tiles:insertAttribute name="content"/>
<script type="text/javascript" src="<c:url value="/resource/js/contextmenu.js"/>"></script>
</body>
</html>
