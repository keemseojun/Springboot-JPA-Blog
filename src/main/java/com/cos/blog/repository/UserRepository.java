package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// JPA로 측면에서 생각해보면, 이 클래스는 DAO라고 생각하면됨
// 자동으로 bean 등록이 됨. 그러므로 @Repository 어노테이션 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {		//User 테이블의 Primary key는 Integer임.
	
}
