$(document).ready(function () {
    $("#encryption_button").on("click", function () {

        var param = { text : "Test Text"};

        $.ajax({
            type : "post",
            data : param,
            url : "/encryption",
            success : function () {
                alert("ajax sucess")
            },
            error : function () {
                alert("error")
            }
        });
    });
});