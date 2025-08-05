package com.pro.springboot.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// 누구든 접속 가능
	@GetMapping("/guest/index.do")
	public String welcome1() {
		return "login/guest";
	}
	
	// ADMIN, USER 중 하나의 권한만 있으면 접속 가능
	@GetMapping("/member/index.do")
	public String welcome2() {
		return "login/member";
	}
	
	// ADMIN 권한만 접속 가능
	@GetMapping("/admin/index.do")
	public String welcome3() {
		return "login/admin";
	}
	
	// 커스텀 로그인 페이지 매핑
	@GetMapping("/myLogin.do")
	public String login1(Principal principal, Model model) {
		try {
			String user_id = principal.getName();
			model.addAttribute("user_id", user_id);
		}
		catch (Exception e) {
			System.out.println("로그인 전입니다.");
		}
		
		return "auth/login";
	}
	
	// 로그인 시도 중 에러가 발생하는 경우
	@GetMapping("/myError.do")
	public String login2() {
		return "auth/error";
	}
	
	// 권한이 부족한 경우
	@GetMapping("/denied.do")
	public String login3() {
		return "auth/denied";
	}
}
