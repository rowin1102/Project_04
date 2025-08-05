package com.pro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pro.springboot.jdbc.MemberDTO;
import com.pro.springboot.jdbc.MemberService;

@Controller
public class RegistController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/regist.do")
	public String registGet() {
		return "regist/regist";
	}
	
	@PostMapping("/regist.do")
	public String registPost(MemberDTO memberDTO) {
		memberService.register(memberDTO);
		
		return "main";
	}
}
