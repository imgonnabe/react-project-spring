package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 *  회원가입
	 */
	public Long join(Member member) {// Optional로 바로 반환하는 것 좋지 않음
		validateDuplicateMember(member);// 중복회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {// 메서드 추출: alt + shift + M
		// Optional<Member> result = memberRepository.findByName(member.getName());
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {// Optional로 감쌌기 때문에 ifPresent, 기존에는 if null이 아니면
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/**
	 *	전체 회원 조회 
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
