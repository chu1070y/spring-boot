package org.salem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

//어노테이션 만으로도 세팅가능

@EnableWebSecurity
@Log
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize("isAnonymous()")이거 실행시킬건지 얘기해주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new SalemUserService();
	}
	
	//옛날버전에서는 필요했지만 지금 스프링 버전에서는 안써줘도 알아서 다한다.
/*	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.warning("================= configureGlobal===============");
		
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("----------------");
		log.info("configure");
		log.info("----------------");
		
		//스프링에서 디폴트로 제공하는 로그인 페이지
		http.formLogin();
		
		http.rememberMe().tokenValiditySeconds(60*60*24);
		
	}
	
}
