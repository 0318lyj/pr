/**
 * main이라는 객체를 만들어 그 안에 기능을 선언
 * 이렇게 하는 이유는 다른 js파일과 변수명이 겹쳐서 발생하는 '브라우저 전역 변수 충돌'을 방지하기 위함
 */
/**
 * index.js - Vanilla JS & Fetch API Version
 * jQuery 의존성을 완전히 제거한 버전.
 */
const main = {
    // 1. 이벤트 리스너 등록 (초기화)
    init: function () {
        // querySelector는 CSS 선택자 문법을 사용하므로 ID 앞에 '#'을 꼭 붙여야 한다.
        const btnSave = document.querySelector('#btn-save');
        const btnUpdate = document.querySelector('#btn-update');
        const btnDelete = document.querySelector('#btn-delete');
        const btnUserSave = document.querySelector('#btn-user-save');

        // 각 요소가 현재 페이지에 존재할 때만 리스너를 등록합니다.
        if (btnSave) {
            btnSave.addEventListener('click', () => this.save());
        }
        if (btnUpdate) {
            btnUpdate.addEventListener('click', () => this.update());
        }
        if (btnDelete) {
            btnDelete.addEventListener('click', () => this.delete());
        }
        if (btnUserSave) {
            btnUserSave.addEventListener('click', () => this.userSave());
        }
    },

    // 2. 게시글 등록
    save: function () {
        const data = {
            title: document.querySelector('#title').value,
            content: document.querySelector('#content').value
        };

        // fetch는 Promise 기반의 비동기 통신
        fetch('/api/v1/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) throw new Error('등록 중 오류가 발생했습니다.');
            return response.json();
        })
        .then(() => {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        })
        .catch(error => alert(error.message));
    },

    // 3. 게시글 수정
    update: function () {
        const data = {
            title: document.querySelector('#title').value,
            content: document.querySelector('#content').value
        };
        const id = document.querySelector('#id').value;

        fetch('/api/v1/posts/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) throw new Error('수정 권한이 없거나 오류가 발생했습니다.');
            return response.json();
        })
        .then(() => {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        })
        .catch(error => alert(error.message));
    },

    // 4. 게시글 삭제
    delete: function () {
        const id = document.querySelector('#id').value;

        fetch('/api/v1/posts/' + id, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) throw new Error('삭제 권한이 없거나 오류가 발생했습니다.');
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        })
        .catch(error => alert(error.message));
    },

    // 5. 회원가입
    userSave: function () {
        const data = {
            email: document.querySelector('#email').value,
            password: document.querySelector('#password').value,
            role: document.querySelector('#role').value
        };

        fetch('/api/v1/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) throw new Error('이미 존재하는 이메일이거나 가입 형식이 잘못되었습니다.');
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/login';
        })
        .catch(error => alert(error.message));
    },

    // 6. 관리자 - 회원 추방
    userDelete: function (id) {
        if (!confirm("정말 이 회원을 추방하시겠습니까?")) return;

        fetch('/api/v1/admin/user/' + id, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) throw new Error('추방 권한이 없거나 오류가 발생했습니다.');
            alert('회원이 추방되었습니다.');
            location.reload();
        })
        .catch(error => alert(error.message));
    }
};

// DOMContentLoaded: 브라우저가 HTML을 모두 읽고 DOM 트리를 완성한 즉시 실행
document.addEventListener('DOMContentLoaded', () => {
    main.init();
});



    // Ajax 기반
    // save : function () {
    //     //화면에서 사용자가 입력한 값들을 가져와서 데이터 객체로 만든다.
    //     var data = {
    //         title: $('#title').val(),
    //         author: $('#author').val(),
    //         content: $('#content').val()
    //     };

    //     // 비동기 통신(Ajax)을 시작
    //     $.ajax({
    //         type: 'POST',
    //         url: '/api/v1/posts',
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8',
    //         data: JSON.stringify(data)
    //     }).done(function() {
    //         //통신이 성공했을 때 실행되는 로직
    //         alert('글이 등록되었습니다.');
    //         window.location.href = '/'; //등록 성공 후 메인 화면으로 이동
    //     }).fail(function (error){
    //         //통신이 실패했을 때 실행되는 로직
    //         alert(JSON.stringify(error));
    //     });
    // },
    // update : function () {
    //     var data = {
    //         title: $('#title').val(),
    //         content:$('#content').val()
    //     };

    //     var id = $('#id').val();

    //     $.ajax({
    //         type:'PUT', //수정은 PUT
    //         url: '/api/v1/posts/' +id,
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8',
    //         data: JSON.stringify(data)
    //     }).done(function(){
    //         alert('글이 수정되었습니다.');
    //         window.location.href = '/';
    //     }).fail(function (error) {
    //         alert(JSON.stringify(error));
    //     })
    // },
    // delete : function () {
    //     var id = $('#id').val();

    //     $.ajax({
    //         type: 'DELETE', //삭제는 DELETE
    //         url: '/api/v1/posts/' + id,
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8'
    //     }).done(function(){
    //         alert('글이 삭제되었습니다.');
    //         window.location.href = '/';
    //     }).fail(function (error){
    //         alert(JSON.stringify(error));
    //     });
    // },
    // userSave : function (){
    //     var data = {
    //         email: $('#email').val(),
    //         password: $('#password').val(),
    //         role: $('#role').val()
    //     };

    //     $.ajax({
    //         type: 'POST',
    //         url: '/api/v1/user',
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8',
    //         data: JSON.stringify(data)
    //     }).done(function(){
    //         alert('회원가입이 완료되었습니다.');
    //         window.location.href = '/login'; //가입 후 로그인 페이지로 이동
    //     }).fail(function (error){
    //         alert(JSON.stringify(error));
    //     });
    // },
    // userDelete : function (id) {
    //     $.ajax({
    //         type: 'DELETE',
    //         url: '/api/v1/admin/user/' + id,
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8'
    //     }).done(function() {
    //         alert('회원이 추방되었습니다.');
    //         location.reload(); //페이지 새로고침
    //     }).fail(function (error){
    //         alert(JSON.stringify(error));
    //     });
    // }
