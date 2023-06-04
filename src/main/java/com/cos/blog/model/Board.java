package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity		// 클래스가 ORM임을 명시해주는 어노테이션이 클래스에 가까이 있는게 좋음. 직관적으로 ORM 클래스라는것에 대한 가독성을 높이기위해서 !!

public class Board {

	@Id		// primary key니까 @Id 해야함
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob		// 대용량 데이터를 쓸 때 사용하는 어노테이션
	private String content;	// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
												// '섬머노트'라는 라이브러리를 사용할거임. 이 라이브러리는 <html>태그가 섞여서 디자인 되는데 이 태그의 용량이 커서 Lob 어노테이션 사용해야함.
	
	@ColumnDefault("0")
	private int count;		// 게시판 조회수
	
	@ManyToOne(fetch=FetchType.EAGER)		
	// Many = Board, User = One  (한 명의 유저는 여러개의 글을 생성할 수 있다)
	// fetch타입이 EAGER 전략임. Board 테이블을 select 하면 유저정보를 무조건 가져오라는 뜻임. 기본적으로 세팅되어있는 fetch 전략은 EAGER 전략임.
	// 그래서 걍 fetch 전략 안써있으면 EAGER 전략임
	@JoinColumn(name="userId")		//실제 DB생성은 userId라는 필드값으로 생성될것이다
	private User user;		//DB는 오브젝트를 저장할 수 없음. Foreign Key(외래키), 자바는 오브젝트롤 저장할 수 있다. 
										// DB는 오브젝트를 저장할 수 없음. 그래서 외래키(Foreign Key)를 사용함. cf) 자바는 오브젝트를 저장할 수 있음.
	
	@OneToMany (mappedBy = "board", fetch=FetchType.EAGER) 	
	// mappedBy가 적혀있으면 "연관관계의 주인이 아니다라는 뜻(=foreign키가 아니라는뜻, =DB에 칼럼을 만들지 말라는뜻)
	// "board"는 Reply클래스에 있는 board임.
	// 1개의 게시글은 여러개의 댓글을 가질 수 있다
	// reply 정보를 안가져 올려면 fetch 전략을 LAZY로 해줘야함
	private List<Reply> reply;		// 1개의 게시글에는 여러개의 댓글이 달릴수있으니까 Reply를 List로 받아야한다. 
	
	@CreationTimestamp		// 데이터가 입력될 때 현재시간이 입력되게 하는 어노테이션
	private Timestamp createTimestamp;
	
}
