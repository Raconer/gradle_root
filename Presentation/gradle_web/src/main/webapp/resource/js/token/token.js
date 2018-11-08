$(document).ready(function() {
    // 토큰 생성
    $('#tokenGenerate').on('click', function () {
        $.ajax({
            type:"post"
            , dataType: "json"
            , contentType: "application/json; charset=UTF-8"
            , url:"/token/generate"
            , success : function (resData) {
                $('#newToken').val(resData.data);
            }
            , error : function (resData) {
                console.log("Token Generate Fail");
            }
        })
    });

    $('#tokenRefresh').on('click', function () {
        var tokenData = $('#newToken').val();
        var param = { token : tokenData }

        console.log(param)
        $.ajax({
            type: "post"
            , url:"/token/refresh"
            , data: param
            , dataType: "json"
            , success : function (resData) {
                $('#refreshToken').val(resData.data);
            }
            , error : function (resData) {
                console.log("Token Generate Fail");
            }
        })
    })
});