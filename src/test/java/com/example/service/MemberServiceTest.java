package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domain.Member;
import com.example.repository.MemoryMemberRepository;

class MemberServiceTest {// 단위 테스트
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach// 메서드가 실행될 때 마다
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void testJoin() {
		// given(준비)
		Member member = new Member();
		member.setName("spring");
		
		// when(실행)
		Long saveId = memberService.join(member);
		
		// then(검증)
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	void 중복회원검사() {
		// given
		Member member1 = new Member();
		member1.setName("hello");
		Member member2 = new Member();
		member2.setName("hello");
		
		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		/*try {
			memberService.join(member2);
			fail("예외가 발생해야 합니다.");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.11");
		}*/
		
		// then
	}

	@Test
	void testFindMembers() {
		fail("Not yet implemented");
	}

	@Test
	void testFindOne() {
		fail("Not yet implemented");
	}

}
