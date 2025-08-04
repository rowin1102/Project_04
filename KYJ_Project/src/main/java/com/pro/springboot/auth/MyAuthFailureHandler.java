package com.pro.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MyAuthFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exp)
		throws IOException, ServletException {
		
		String errorMsg = "";
		
		/* instanceof 연산자를 이용해서 전달된 예외객체의 종류를 파악한 후 적절한 에러메세지를 지정한다.
		 	단, 인증관련 메세지는 너무 자세히 기술하지 않는것이 좋다. */
		if(exp instanceof BadCredentialsException) {
			loginFailureCnt(req.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(1)";
		} else if(exp instanceof InternalAuthenticationServiceException) {
			loginFailureCnt(req.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(2)";
		} else if(exp instanceof DisabledException) {
			errorMsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요(3)";
		} else if(exp instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요(4)";
		}
		
		// 에러메세지를 request 영역에 저장한 후 커스텀 로그인 페이지로 포워드한다.
		req.setAttribute("errorMsg", errorMsg);
		req.getRequestDispatcher("/myLogin.do?error").forward(req, res);
	}
	
	// 각 업무에 맞게 커스텀해서 사용할 수 있는 메서드 정의
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디:" + username);
	}
}
