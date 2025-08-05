<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
    <form action="regist.do" method="post">
        <table border="1">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="user_id" required></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="text" name="email" required></td>
            </tr>
            <tr>
                <td>도메인</td>
                <td><input type="text" name="domain"></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td><input type="text" name="nickname"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="회원가입">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>