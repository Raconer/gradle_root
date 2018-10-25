<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-10-23
  Time: 오후 5:51
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
        <tiles:insertAttribute name="content"/>
    </body>
    <tiles:insertAttribute name="footer"/>
</html>
