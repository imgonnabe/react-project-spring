package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ReactController {
	
	@GetMapping("/write")
	public String write() {
		return "�ȳ��ϼ���.";
	}
}
