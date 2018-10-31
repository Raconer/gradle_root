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
        // Web View Long Press
        var timer = 0;
        $('#viewWrap .postBox .imgArea img').on("mousedown", function () {
            var src = $(this).get(0).currentSrc;
            timer = setTimeout(function () {
            // sendMessageToApp(imgLongPress(src));
            }, 2000);
        }).on("mouseup mouseleave", function () {
            clearTimeout(timer);
        });
    });
