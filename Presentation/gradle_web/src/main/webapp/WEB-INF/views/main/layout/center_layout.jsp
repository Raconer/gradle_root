<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-11-01
  Time: 오후 4:52
  To change this template use File | Settings | File Templates.
--%>

<html>
    <head>
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <tiles:insertAttribute name="header"/>
    </head>
    <body>
        <div class="center">
            <tiles:insertAttribute name="content"/>
        </div>
    </body>
    <tiles:insertAttribute name="footer"/>
</html>
