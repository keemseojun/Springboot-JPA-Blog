package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 비어있는 생성자 생성

public class Member {
	// 객체지향 자바에서는 항상 변수선언을 private으로 해줘야함. final로 "불변성"을 만들어주는거임.
	// 변수는 변수값을 바꾸는것이 아니라 메서드에 의해서 값을 바꿔줘야함.
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder // 빌더패턴
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
