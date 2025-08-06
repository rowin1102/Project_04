package com.pro.springboot.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
	public String login1(Principal principal, Model model,
			@RequestParam(value = "error", required = false) String error) {
		
		
		System.out.println("error : " + error);
		
		if (principal != null) {
	        model.addAttribute("user_id", principal.getName());
	    }
		
		if (error != null) {
	        String errorMsg = null;
	        
	        if ("1".equals(error)) errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(1)";
	        else if ("2".equals(error)) errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요(2)";
	        else errorMsg = "알 수 없는 에러가 발생했습니다.";
	        
	        model.addAttribute("errorMsg", errorMsg);
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
