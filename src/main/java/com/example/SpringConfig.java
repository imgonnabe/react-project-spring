package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repository.MemberRepository;
import com.example.repository.MemoryMemberRepository;
import com.example.service.MemberService;

@Configuration
public class SpringConfig {// 컴포넌트 스캔 말고 직접 등록
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}