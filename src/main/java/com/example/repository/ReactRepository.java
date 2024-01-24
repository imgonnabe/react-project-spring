package com.example.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Board;
import com.example.domain.Comment;

@Mapper
public interface ReactRepository {
	
	List<Board> findAll();
	Board save(Board board);
	Board findById(int bno);
	List<Comment> comment(int bno);
	int login(Map<String, Object> map);
	int join(Map<String, Object> map);
	int idTest(Map<String, Object> map);
	int postWrite(Map<String, Object> map);
	int commentWrite(Map<String, Object> map);
}
