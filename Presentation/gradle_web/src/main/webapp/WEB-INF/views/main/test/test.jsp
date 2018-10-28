<%-- header에 입력했어도 타일즈에 태그입력해야한다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-10-25
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>

<c:forEach var="i" begin="1" end="10" step="1">

<a>
        <img src="https://t1.daumcdn.net/cfile/tistory/99F56B475BBCCBED19"  id="11${i}"  />
    </a>

</c:forEach>




