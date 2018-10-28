    $(document).ready(function() {
        $("img").click(function(){
            alert("이미지 눌렀따 : " + $(this).attr("id"))
        });
    });

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
