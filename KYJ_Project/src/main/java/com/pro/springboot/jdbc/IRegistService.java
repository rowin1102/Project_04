package com.pro.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRegistService {
	
	public int insert(MemberDTO memberDTO);
	
}
