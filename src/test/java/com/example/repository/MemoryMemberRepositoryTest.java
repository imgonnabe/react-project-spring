package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.domain.Member;

class MemoryMemberRepositoryTest {// 테스트는 순서와 관계없이, 의존관계 없이 설계되어야 함
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach// 메서드가 끝날 때마다 실행
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		// System.out.println("result = " + (result == member));
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring1");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring1");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
		// findAll() -> findByName(): findAll()에서 저장한 spring1이 나와서 에러
		// 테스트 끝나면 데이터 클리어해야함
	}
}