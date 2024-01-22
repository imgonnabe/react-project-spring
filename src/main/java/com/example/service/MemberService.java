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
	 *  ȸ������
	 */
	public Long join(Member member) {// Optional�� �ٷ� ��ȯ�ϴ� �� ���� ����
		validateDuplicateMember(member);// �ߺ�ȸ�� ����
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {// �޼��� ����: alt + shift + M
		// Optional<Member> result = memberRepository.findByName(member.getName());
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {// Optional�� ���ձ� ������ ifPresent, �������� if null�� �ƴϸ�
				throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
			});
	}
	
	/**
	 *	��ü ȸ�� ��ȸ 
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
