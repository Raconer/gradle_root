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
    <script src="<c:url value="/resource/js/lib/jquery-3.3.1.min.js"/>"></script>

    <title><tiles:insertAttribute name="location"/></title>
    <script>
        $(document).ready(function(){
            $("p").onclick(

            );
        });
    </script>
</head>
<body>
<p>If you click on me, I will disappear.</p>
<p>Click me away!</p>
<p>Click me too!</p>

${listCnt}
<tiles:insertAttribute name="content"/>
</body>
</html>
