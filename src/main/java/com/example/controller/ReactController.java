package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Board;
import com.example.domain.Comment;
import com.example.service.ReactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ReactController {
	
	private final static Logger logger = LoggerFactory.getLogger(ReactController.class);
	private final ReactService reactService;
	
	@Autowired
	public ReactController(ReactService reactService) {
		this.reactService = reactService;
	}
	
	@GetMapping("/list")
	public String list() {
		List<Board> board = reactService.findAll();
		JSONObject json = new JSONObject();
		json.put("board", board);
		return json.toString();
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") int bno) {
		Board board = reactService.findById(bno);
		// JSONObject json = new JSONObject();
		// json.put("board", board);// board 객체를 json 형식으로 변환하지 않고 그대로 넣으면 {"board":"com.example.domain.Board@3a342b3c"}
		// ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
	    ObjectMapper objectMapper = new ObjectMapper();
	    String json;
		try {
			json = objectMapper.writeValueAsString(board);
			return json.toString();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "에러";
		}
	}
	
	@GetMapping("/comment/{bno}")
	public String comment(@PathVariable("bno") int bno) {
		List<Comment> comment = reactService.comment(bno);
		JSONObject json = new JSONObject();
		json.put("comment", comment);
		return json.toString();
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> map, HttpSession session) {
		logger.info("map: {}", map);
		int count = reactService.login(map);
		if(count == 1) {
			session.setAttribute("id", map.get("id"));
		}
		logger.info("로그인 count: {}", count);
		JSONObject json = new JSONObject();
		json.put("count", count);
		return json.toString();
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		logger.info("로그아웃 ok: {}", "로그아웃");
		JSONObject json = new JSONObject();
		json.put("ok", 1);
		return json.toString();
	}
	
	@PostMapping("/join")
	public String join(@RequestBody Map<String, Object> map) {
		logger.info("map: {}", map);
		JSONObject json = new JSONObject();
		if(map.get("id") == null || map.get("pw") == null || map.get("name") == null ||
			    map.get("id").toString().isEmpty() || map.get("pw").toString().isEmpty() || map.get("name").toString().isEmpty()) {
			json.put("result", "모두 입력하세요.");
		} else if(map.get("isIdTest") == null || !(Boolean) map.get("isIdTest")) {
			json.put("result", "아이디 중복검사하세요.");			
		} else {
			int result = reactService.join(map);
			if(result == 1) {
				logger.info("result: {}", result);
				json.put("회원가입 result", result);
			} else {
				json.put("result", 2);
			}			
		}	
		return json.toString();
	}
	
	@PostMapping("/idTest")
	public String idTest(@RequestBody Map<String, Object> map) {
		logger.info("중복회원검사 id: {}", map.get("id"));
		int count = reactService.idTest(map);
		JSONObject json = new JSONObject();
		if(count != 0) {
			json.put("count", "중복회원있음");
		} else {
			 json.put("count", 0);
		}
		logger.info("중복회원 count: {}", count);
		return json.toString();
	}
	
	@PostMapping("/postWrite")
	public String postWrite(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("id", (String) session.getAttribute("id"));
		logger.info("글쓰기: {}", map);
		int result = reactService.postWrite(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	@PostMapping("/commentWrite")
	public String commentWrite(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("id", (String) session.getAttribute("id"));
		logger.info("댓글쓰기: {}", map);
		int result = reactService.commentWrite(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
}
