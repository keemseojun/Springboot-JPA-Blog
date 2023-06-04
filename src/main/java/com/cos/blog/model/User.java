package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor		// 빈 생성자
@AllArgsConstructor		// 전체 생성자
@Builder		// 빌더 패턴!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성이 되게 할려면 이 어노테이션이 필요함
// @DynamicInsert	//Insert할 때 null인 필드를 제외시켜주는 어노테이션
public class User {

	@Id	// Primary key가 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 뜻
	private int id; 	// 오라클의 시퀀스랑 같은개념 or MySQL의 auto_increment랑 같은 개념
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;	// 아이디
	
	@Column(nullable = false, length = 100)	// 패스워드를 "해쉬"로 바꾸어서 "암호화"할거임.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("user")
	// DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)	// 어노테이션으로 아래 RoleType이 String이라는것을 알려주는거임
	private RoleType role;	// Enum을 쓰는게 좋음. 여기서 role이란 admin, user, manager처럼 권한을 주는거임.
	
	@CreationTimestamp	// 이 어노테이션이 테이블을 insert할 때 시간을 자동으로 입력되게함
	private Timestamp createDate;
}
