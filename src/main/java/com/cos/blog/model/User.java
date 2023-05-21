package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor		// 빈 생성자
@AllArgsConstructor		// 전체 생성자
@Builder		// 빌더 패턴!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성이 됨.

public class User {

	@Id	// Primary key가 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 뜻
	private int id; 	// 오라클의 시퀀스랑 같은개념 or MySQL의 auto_increment랑 같은 개념
	
	@Column(nullable = false, length = 30)
	private String username;	// 아이디
	
	@Column(nullable = false, length = 100)	// 패스워드를 "해쉬"로 바꾸어서 "암호화"할거임.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")		// DB에서 character로 사용할 예정이라서 쌍따옴표 안에 홑따옴표가 있는거임.
	private String role;	// Enum을 쓰는게 좋음. 여기서 role이란 admin, user, manager처럼 역할을 주는거임.
	
	@CreationTimestamp	// 이 어노테이션이 시간이 자동으로 입력되게함
	private Timestamp createDate;
}
