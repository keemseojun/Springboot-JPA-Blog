package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Board {

	@Id		// primary key니까 @Id 해야함
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob		// 대용량 데이터를 쓸 때 사용하는 어노테이션
	private String content;	// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count;		// 게시판 조회수
	
	@ManyToOne		// Many = Board, User = One  (한 명의 유저는 여러개의 글을 생성할 수 있다)
	@JoinColumn(name="userId")		//실제 DB생성은 userId라는 필드값으로 생성될것이다
	private User user;		//DB는 오브젝트를 저장할 수 없음. Foreign Key(외래키), 자바는 오브젝트롤 저장할 수 있다.
	
	@CreationTimestamp		// 데이터가 입력될 때 현재시간이 입력되게 하는 어노테이션
	private Timestamp createTimestamp;
	
}
