/**
 * main이라는 객체를 만들어 그 안에 기능을 선언
 * 이렇게 하는 이유는 다른 js파일과 변수명이 겹쳐서 발생하는 '브라우저 전역 변수 충돌'을 방지하기 위함
 */
var main = {
    init : function () {
        var _this = this;

        // '등록' 버튼(id="btn-save")을 클릭하면 save 함수를 실행하도록 이벤트를 연결
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        //화면에서 사용자가 입력한 값들을 가져와서 데이터 객체로 만든다.
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        // 비동기 통신(Ajax)을 시작
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            //통신이 성공했을 때 실행되는 로직
            alert('글이 등록되었습니다.');
            window.location.href = '/'; //등록 성공 후 메인 화면으로 이동
        }).fail(function (error){
            //통신이 실패했을 때 실행되는 로직
            alert(JSON.stringify(error));
        });
    }
};

//위에서 정의한 init 함수를 실행한다.
main.init();