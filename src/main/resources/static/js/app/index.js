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

        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-user-save').on('click', function () {
            _this.userSave();
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
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content:$('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type:'PUT', //수정은 PUT
            url: '/api/v1/posts/' +id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE', //삭제는 DELETE
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    userSave : function (){
        var data = {
            email: $('#email').val(),
            password: $('#password').val(),
            role: $('#role').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/user',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/login'; //가입 후 로그인 페이지로 이동
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

//위에서 정의한 init 함수를 실행한다.
main.init();