package com.pro.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
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
		
		if(exp instanceof BadCredentialsException) {
			loginFailureCnt(req.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(1)";
		} else if(exp instanceof InternalAuthenticationServiceException) {
			loginFailureCnt(req.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(2)";
		} 
		
		req.setAttribute("errorMsg", errorMsg);
		req.getRequestDispatcher("/myLogin.do?error").forward(req, res);
	}
	
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디:" + username);
	}
}
