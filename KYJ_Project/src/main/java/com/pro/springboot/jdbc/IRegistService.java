package com.pro.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRegistService {
	
	public int insert(MemberDTO memberDTO);
	
	public int checkDuplicateId(@Param("userId") String userId);
	
	public int checkDuplicateNickname(@Param("nickname") String nickname);
}
