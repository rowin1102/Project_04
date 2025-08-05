package com.pro.springboot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private IRegistService iRegistService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void register(MemberDTO memberDTO) {
		String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encodedPassword);
		
		iRegistService.insert(memberDTO);
	}
}
