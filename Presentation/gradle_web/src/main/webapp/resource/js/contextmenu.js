$(document).ready(function () {
    $( "img" ).bind( "taphold", function () {
        alert("test : " + this.menubar)
    });
/*    window.oncontextmenu = function (ev) {
        ev.preventDefault();
        ev.stopPropagation();

        return false;
    }*/
});