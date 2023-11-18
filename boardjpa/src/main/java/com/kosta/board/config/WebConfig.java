package com.kosta.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE");
		//allowedOrigins에 리액트 서버 포트
		//method는 대문자로 안 써도 됨. 소문자로 써도 무방함. 아무것도 안 써주면 get, post
		//근데 put과 delete만 쓰면 get, post를 못 씀
		//다 써줄 거면 get, post도 다 써줘야 함
		
	}
}
