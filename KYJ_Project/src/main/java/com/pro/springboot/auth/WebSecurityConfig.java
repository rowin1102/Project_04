package com.pro.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.cors(csrf -> csrf.disable())
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/", "/regist.do", "/checkDuplicate.do", "/checkNicknameDuplicate.do").permitAll()
					.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
					.requestMatchers("/guest/**").permitAll()
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
		);
		
		http.formLogin(formLogin -> formLogin
				.loginPage("/myLogin.do")
				.loginProcessingUrl("/myLoginAction.do")
				.defaultSuccessUrl("/", true)
				.failureHandler(myAuthFailureHandler)
				.usernameParameter("my_id")
				.passwordParameter("my_pass")
				.permitAll()
		);
		
		http.logout(logout -> logout
				.logoutUrl("/myLogout.do")
				.logoutSuccessUrl("/")
				.permitAll()
		);
		
		http.exceptionHandling(expHandling -> expHandling
				.accessDeniedPage("/denied.do"));
		
		return http.build();
		
	}
	
	@Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select user_id, password, 1 as enabled from member where user_id = ?");
        manager.setAuthoritiesByUsernameQuery("select user_id, member_auth from member where user_id = ?");
        return manager;
    }
}

