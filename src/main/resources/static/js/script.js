// 아이디 중복체크
$(function () {
    $("#idCheck").click(function () {
        var id = $("#id").val();  // 사용자가 입력한 아이디 값 가져오기

        // 아이디가 비어 있는지 확인
        if (id === "") {
            $("#result").text("아이디를 입력해주세요.");
            return;
        }

        // AJAX 요청
        $.ajax({
            url: '/idCheck',  // 서버에서 처리할 URL
            type: 'GET',      // 요청 방식
            data: {id: id}, // 서버로 보낼 데이터 (아이디)
            success: function (response) {
                $("#result").text(response);
            },
            error: function () {
                // 요청 실패 시 에러 메시지 출력
                $("#result").text("에러가 발생했습니다.");
            }
        });
    });
});

//input미입력 시 제출버튼 불기능
document.addEventListener('DOMContentLoaded', function () {
    // 폼 요소와 제출 버튼 가져오기
    const form = document.getElementById('signupForm');
    const submitBtn = document.getElementById('submitBtn');

    // 모든 입력 필드를 가져오기
    const inputs = form.querySelectorAll('input[required]');

    // 각 입력 필드에서 이벤트 리스너 추가
    inputs.forEach(input => {
        input.addEventListener('input', checkForm);
    });

    // 폼 상태 체크 함수
    function checkForm() {
        // 모든 입력 필드가 올바르게 작성되었는지 확인
        let allFilled = true;
        inputs.forEach(input => {
            if (!input.value.trim()) {
                allFilled = false;
            }
        });

        // 모든 필드가 작성되었으면 제출 버튼 활성화, 아니면 비활성화
        submitBtn.disabled = !allFilled;
    }

    // 폼 초기 상태로 제출 버튼 비활성화
    checkForm();
});

//pw != pwCheck 일 때 버튼 불기능

$(function (){
    $("#submitBtn").click(function (){
        console.log("click");

        const pw = document.getElementById("pw");
        const pwCheck = document.getElementById("pwCheck");
        const submitBtn = document.getElementById('submitBtn');

        if (pw != pwCheck){
            submitBtn.disabled = true;
            $("#pwResult").text("비밀번호가 일치하지 않습니다");
        }else {
            submitBtn.disabled = false;
        }
    })
})