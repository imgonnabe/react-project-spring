package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.domain.Member;

public class MemoryMemberRepository implements MemberRepository {
	
	private static Map<Long, Member> store = new HashMap<Long, Member>();// �ǹ������� ConcurrentHashMap(���ü� ���� �ذ�)
	private static long sequence = 0L;// 0�� ���������� int���� �ǹ�, 0L�� long��, �ǹ������� AtomicLong(���ü� ���� �ذ�), key�� ����
	
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
		// store.values(): store �ʿ� ����� ��� Member ��ü�� �÷����� ������
		// stream: ��Ʈ���� ����
		// filter: �־��� �̸��� ��ġ�ϴ� ����� ã��
		// findAny: ã�� ��� �� �ƹ� ���̳� �ϳ��� ����, ��ġ�ϴ� ����� ���ٸ� Optimal�� ���� ����� ��ȯ
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
