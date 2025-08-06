<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/css/regist.css">
<script src="/js/regist.js"></script>
</head>
<body>
	<div class="signup-container">
		<h2>회원가입</h2>
		<form action="regist.do" method="post"
			onsubmit="return validateForm()">
			<table class="form-table">
				<tr>
					<td class="required">아이디</td>
					<td>
						<div class="input-container">
							<input type="text" name="user_id" class="form-input input-field"
								required placeholder="아이디를 입력하세요">
							<button type="button" class="check-btn"
								onclick="checkIdDuplicate()">중복확인</button>
						</div>
						<div id="idMessage" class="validation-message"></div>
					</td>
				</tr>
				<tr>
					<td class="required">비밀번호</td>
					<td><input type="password" name="password" class="form-input"
						required placeholder="비밀번호를 입력하세요"></td>
				</tr>
				<tr>
					<td class="required">비밀번호 확인</td>
					<td><input type="password" name="password_confirm"
						class="form-input" required placeholder="비밀번호를 다시 입력하세요"
						onblur="checkPasswordMatch()">
						<div id="passwordMessage" class="validation-message"></div></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>
						<div class="input-container">
							<input type="text" name="nickname" class="form-input input-field"
								placeholder="닉네임을 입력하세요">
							<button type="button" class="check-btn"
								onclick="checkNicknameDuplicate()">중복확인</button>
						</div>
						<div id="nicknameMessage" class="validation-message"></div>
					</td>
				</tr>
				<tr>
					<td class="required">이메일</td>
					<td>
						<div class="email-container">
							<input type="text" name="email" class="form-input email-input"
								required placeholder="이메일"> <span class="at-symbol">@</span>
							<input type="text" name="domain" class="form-input domain-input"
								placeholder="직접입력" id="domainInput">
						</div>
					</td>
				</tr>
				<tr>
					<td>이메일 도메인</td>
					<td><select name="domain_select" class="form-input"
						onchange="handleDomainSelect(this.value)">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
					</select></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="phone" class="form-input"
						placeholder="010-1234-5678" onblur="validatePhoneFormat()">
						<div id="phoneMessage" class="validation-message"></div></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="회원가입"
						class="submit-btn"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>