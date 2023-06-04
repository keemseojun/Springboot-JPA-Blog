package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice		// 프로젝트 전체에서 예외가 발생하면 지금 클래스로 예외가 오게할려면 이 어노테이션을 삽입해야함
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
