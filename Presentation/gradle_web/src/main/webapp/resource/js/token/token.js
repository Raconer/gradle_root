$(document).ready(function() {
    console.log("111")
    // 토큰 생성
    $('#tokenGenerate').on('click', function () {
        $.ajax({
            type:"post"
            , dataType: "json"
            , contentType: "application/json; charset=UTF-8"
            , url:"/token/generate"
            , success : function (resData) {
                console.log("Token Generate Sucess : " + resData.data);
                $('#newToken').val(resData.data);
            }
            , error : function (resData) {
                console.log("Token Generate Fail");
            }
        })
    });
});