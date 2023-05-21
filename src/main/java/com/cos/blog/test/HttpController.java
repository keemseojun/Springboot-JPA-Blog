package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청하면 그에 대한 응답을 "HTML"로 하는것이 "@Controller"임.
//사용자가 요청하면 그에 대한 응답을 "Data"로 하는것이 "@RestController"임.
@RestController
public class HttpController {

	private static final String TAG ="HttpController : ";
	
	// localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저로 요청하는 것은 무조건 "GET"으로 해야함 !!
	//http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get 요청 : " +m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/post(insert)
	@PostMapping("/http/post") //  1. text/plain 2. application/json
	public String postTest(@RequestBody Member m) { // 스프링부트의 MessageConverter 클래스가 자동으로 응답형식을 변환해줌
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " +m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
