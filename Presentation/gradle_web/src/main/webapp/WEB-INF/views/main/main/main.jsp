<%--
  Created by IntelliJ IDEA.
  User: dhokim
  Date: 2018-10-19
  Time: 오후 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p>If you click on me, I will disappear.</p>
<p>Click me away!</p>
<p>Click me too!</p>
<style>
    html, body { padding: 0; margin: 0; }
    div.box {
        width: 3em;
        height: 3em;
        background-color: #108040;
    }
</style>

<h3>Long press the square for 750 milliseconds to see the above code applied:</h3>
<div class="box"></div>

<a>
    <img src="https://t1.daumcdn.net/cfile/tistory/99F56B475BBCCBED19"  />
</a>


<script>
    $(function(){
        window.oncontextmenu = function (ev) {
            ev.preventDefault();
            ev.stopPropagation();
            return false;
        }
        // taphold, click 여러가지 이벤트 사용가능
        /*$( "img" ).bind( "taphold", function () {
            alert("tt4");
        });*/
        /*$( "img" ).bind( "contextMenu", function () {
            alert("tt4");
        });*/
    });
</script>