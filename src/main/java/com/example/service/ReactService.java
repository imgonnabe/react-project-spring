package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Board;
import com.example.domain.Comment;
import com.example.repository.ReactRepository;

@Service
public class ReactService {
	
	private final ReactRepository reactRepository;
	
	@Autowired
	public ReactService(ReactRepository reactRepository) {
		this.reactRepository = reactRepository;
	}

	public List<Board> findAll() {
		return reactRepository.findAll();
	}

	public List<Comment> comment(int bno) {
		return reactRepository.comment(bno);
	}

	public Board findById(int bno) {
		return reactRepository.findById(bno);
	}

	public int login(Map<String, Object> map) {
		return reactRepository.login(map);
	}

	public int join(Map<String, Object> map) {
		return reactRepository.join(map);
	}

	public int idTest(Map<String, Object> map) {
		return reactRepository.idTest(map);
	}

	public int postWrite(Map<String, Object> map) {
		return reactRepository.postWrite(map);
	}

	public int commentWrite(Map<String, Object> map) {
		return reactRepository.commentWrite(map);
	}
}
