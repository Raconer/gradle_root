    $(document).ready(function() {
      /*  alert("tttt");*/
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
