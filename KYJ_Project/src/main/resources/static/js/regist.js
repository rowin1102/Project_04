function handleDomainSelect(value) {
    const domainInput = document.getElementById('domainInput');
    if (value) {
        domainInput.value = value;
        domainInput.readOnly = true;
        domainInput.style.backgroundColor = '#f0f0f0';
    } else {
        domainInput.value = '';
        domainInput.readOnly = false;
        domainInput.style.backgroundColor = '#fafafa';
    }
}

// 중복확인 상태를 추적하는 변수
let isIdChecked = false;
let isNicknameChecked = false;

// 아이디 중복확인 함수
function checkIdDuplicate() {
    const userIdInput = document.querySelector('input[name="user_id"]');
    const messageDiv = document.getElementById('idMessage');
    const userId = userIdInput.value.trim();
    
    if (!userId) {
        messageDiv.textContent = '아이디를 입력해주세요.';
        messageDiv.className = 'validation-message error';
        userIdInput.focus();
        return;
    }
    
    // 중복확인 버튼 비활성화 (중복 요청 방지)
    const checkBtn = event.target;
    checkBtn.disabled = true;
    checkBtn.textContent = '확인중...';
    
    // AJAX로 서버에 중복확인 요청 (Spring Boot)
    fetch('/checkDuplicate.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'user_id=' + encodeURIComponent(userId)
    })
    .then(response => response.json())
    .then(data => {
        if (data.available) {
            messageDiv.textContent = data.message || '사용가능합니다.';
            messageDiv.className = 'validation-message success';
            isIdChecked = true;
        } else {
            messageDiv.textContent = data.message || '아이디를 다시 입력해주세요.';
            messageDiv.className = 'validation-message error';
            isIdChecked = false;
        }
    })
    .catch(error => {
        console.error('Error:', error);
        messageDiv.textContent = '중복확인 중 오류가 발생했습니다.';
        messageDiv.className = 'validation-message error';
        isIdChecked = false;
    })
    .finally(() => {
        // 중복확인 버튼 다시 활성화
        checkBtn.disabled = false;
        checkBtn.textContent = '중복확인';
    });
}

// 닉네임 중복확인 함수
function checkNicknameDuplicate() {
    const nicknameInput = document.querySelector('input[name="nickname"]');
    const messageDiv = document.getElementById('nicknameMessage');
    const nickname = nicknameInput.value.trim();
    
    if (!nickname) {
        messageDiv.textContent = '닉네임을 입력해주세요.';
        messageDiv.className = 'validation-message error';
        nicknameInput.focus();
        return;
    }
    
    // 중복확인 버튼 비활성화 (중복 요청 방지)
    const checkBtn = event.target;
    checkBtn.disabled = true;
    checkBtn.textContent = '확인중...';
    
    // AJAX로 서버에 중복확인 요청 (Spring Boot)
    fetch('/checkNicknameDuplicate.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'nickname=' + encodeURIComponent(nickname)
    })
    .then(response => response.json())
    .then(data => {
        if (data.available) {
            messageDiv.textContent = data.message || '사용가능합니다.';
            messageDiv.className = 'validation-message success';
            isNicknameChecked = true;
        } else {
            messageDiv.textContent = data.message || '닉네임을 다시 입력해주세요.';
            messageDiv.className = 'validation-message error';
            isNicknameChecked = false;
        }
    })
    .catch(error => {
        console.error('Error:', error);
        messageDiv.textContent = '중복확인 중 오류가 발생했습니다.';
        messageDiv.className = 'validation-message error';
        isNicknameChecked = false;
    })
    .finally(() => {
        // 중복확인 버튼 다시 활성화
        checkBtn.disabled = false;
        checkBtn.textContent = '중복확인';
    });
}

function checkPasswordMatch() {
    const password = document.querySelector('input[name="password"]').value;
    const passwordConfirm = document.querySelector('input[name="password_confirm"]').value;
    const messageDiv = document.getElementById('passwordMessage');
    
    if (!passwordConfirm) {
        messageDiv.textContent = '';
        messageDiv.className = 'validation-message';
        return;
    }
    
    if (password === passwordConfirm) {
        messageDiv.textContent = '비밀번호가 일치합니다.';
        messageDiv.className = 'validation-message success';
    } else {
        messageDiv.textContent = '비밀번호가 일치하지 않습니다.';
        messageDiv.className = 'validation-message error';
    }
}

// 아이디 입력값이 변경되면 중복확인 상태를 초기화
document.addEventListener('DOMContentLoaded', function() {
    const userIdInput = document.querySelector('input[name="user_id"]');
    userIdInput.addEventListener('input', function() {
        isIdChecked = false;
        const messageDiv = document.getElementById('idMessage');
        messageDiv.textContent = '';
        messageDiv.className = 'validation-message';
    });
    
    // 닉네임 입력값이 변경되면 중복확인 상태를 초기화
    const nicknameInput = document.querySelector('input[name="nickname"]');
    nicknameInput.addEventListener('input', function() {
        isNicknameChecked = false;
        const messageDiv = document.getElementById('nicknameMessage');
        messageDiv.textContent = '';
        messageDiv.className = 'validation-message';
    });
});

// 폼 제출 시 검증 함수
function validateForm() {
    const nicknameInput = document.querySelector('input[name="nickname"]');
    const hasNickname = nicknameInput.value.trim() !== '';
    
    if (!isIdChecked) {
        alert('아이디 중복확인을 해주세요');
        document.querySelector('input[name="user_id"]').focus();
        return false;
    }
    
    // 닉네임이 입력되었으나 중복확인이 안된 경우
    if (hasNickname && !isNicknameChecked) {
        alert('닉네임 중복확인을 해주세요');
        nicknameInput.focus();
        return false;
    }
    
    return true;
}

// 전화번호 형식 검증 함수
function validatePhoneFormat() {
    const phoneInput = document.querySelector('input[name="phone"]');
    const phoneMessage = document.getElementById('phoneMessage');
    const phone = phoneInput.value.trim();
    
    // 전화번호가 비어있으면 검증하지 않음 (선택사항이므로)
    if (!phone) {
        phoneMessage.textContent = '';
        phoneMessage.className = 'validation-message';
        return;
    }
    
    // 000-0000-0000 형식 검증 (정규표현식)
    const phonePattern = /^\d{3}-\d{4}-\d{4}$/;
    
    if (phonePattern.test(phone)) {
        phoneMessage.textContent = '';
        phoneMessage.className = 'validation-message';
    } else {
        phoneMessage.textContent = '000-0000-0000 형식에 맞게 입력해주세요.';
        phoneMessage.className = 'validation-message error';
    }
}