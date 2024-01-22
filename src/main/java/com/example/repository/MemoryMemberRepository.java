package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.domain.Member;

public class MemoryMemberRepository implements MemberRepository {
	
	private static Map<Long, Member> store = new HashMap<Long, Member>();// 실무에서는 ConcurrentHashMap(동시성 문제 해결)
	private static long sequence = 0L;// 0은 묵시적으로 int값을 의미, 0L은 long형, 실무에서는 AtomicLong(동시성 문제 해결), key값 생성
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
		// store.values(): store 맵에 저장된 모든 Member 객체의 컬렉션을 가져옴
		// stream: 스트림을 생성
		// filter: 주어진 이름과 일치하는 멤버를 찾음
		// findAny: 찾은 멤버 중 아무 것이나 하나를 선택, 일치하는 멤버가 없다면 Optimal을 감싼 결과를 반환
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Member>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}

}
