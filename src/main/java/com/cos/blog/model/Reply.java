package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id							//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는뜻
	private int id;		// 시퀀스, auto_increment
	
	@Column(nullable = false, length=200)
	private String content;
	
	@ManyToOne // 여러개의 답변(Many)은 하나의 게시글(One)에 존재할 수 있다.
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변(Many)은 하나의 유저(One)가 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user;  // DB는 오브젝트를 저장할 수 없음. 그래서 외래키(Foreign Key)를 사용함. cf) 자바는 오브젝트를 저장할 수 있음.
	
	@CreationTimestamp
	private Timestamp createDate;
}
