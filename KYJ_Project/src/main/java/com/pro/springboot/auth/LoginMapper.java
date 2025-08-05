package com.pro.springboot.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pro.springboot.jdbc.MemberDTO;

@Mapper
public interface LoginMapper {
	MemberDTO selectIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
