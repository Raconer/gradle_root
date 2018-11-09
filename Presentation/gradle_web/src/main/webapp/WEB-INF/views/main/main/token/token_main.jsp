<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-11-01
  Time: 오후 5:17
  To change this template use File | Settings | File Templates.
--%>
<div class="max_size t_right">
    <textarea id="newToken" class="max_size">${value}</textarea>
    <input type="button" id="tokenGenerate" value="token 생성"/>
</div>
<div class="max_size t_center ">
    <input type="button"  id="tokenRefresh"  style=" margin-bottom: 11px;"value="token 갱신"/>
</div>
<div class="max_size t_right">
    <textarea id="refreshToken" class="max_size" ></textarea>
</div>
