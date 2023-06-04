package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})		
	// "" -> 아무것도 입력하지 않으면 index를 리턴함
	// "/" -> /를 붙이면 아래 index를 리턴함
	public String index() {
		// 접두어 : /WEB-INF/views/
		// 접미어 : .jsp
		return "index";
	}
}
