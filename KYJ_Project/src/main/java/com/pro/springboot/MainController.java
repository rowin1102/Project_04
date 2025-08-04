package com.pro.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pro.springboot.jdbc.IRegistService;
import com.pro.springboot.jdbc.MemberDTO;

@Controller
public class MainController {
	
	@Autowired
	private IRegistService iRegistService;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/regist.do")
	public String registGet() {
		return "regist";
	}
	
	@PostMapping("/regist.do")
	public String registPost(MemberDTO memberDTO) {
		iRegistService.insert(memberDTO);
		
		return "main";
	}
	
}