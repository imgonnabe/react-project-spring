package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

@SpringBootTest// 스프링 컨테이너와 테스트를 함께 실행, 통합 테스트
@Transactional// 트랜잭션 먼저 실행 -> 테스트 -> 롤백
class MemberServiceIntegrationTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	/*@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach// 메서드가 실행될 때 마다
	public void afterEach() {
		memberRepository.clearStore();
	}*/

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

}
