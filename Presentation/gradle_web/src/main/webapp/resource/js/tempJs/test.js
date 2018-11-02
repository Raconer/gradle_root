$(document).ready(function() {
    // Web View Long Press
    var timer = 0;
    $('img').bind("touchstart", function () {
        var src = $(this).get(0).currentSrc;
        timer = setTimeout(function () {
            alert("테스트 프레스");
            // sendMessageToApp(imgLongPress(src));
        }, 2000);
    }).bind("touchend", function () {
        clearTimeout(timer);
    });
});



/*
window.oncontextmenu = function (ev) {
    ev.preventDefault();
    ev.stopPropagation();
    return false;
}
*/


