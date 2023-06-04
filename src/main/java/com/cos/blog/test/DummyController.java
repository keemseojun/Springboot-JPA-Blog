package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyController {
	
	@Autowired		// 의존성 주입(DI)
	private UserRepository userRepository;
	
	// save 함수는 id를 전달하지않으면 insert를 해줌
	// save 함수는 id를 전달하면, 해당 id에 대한 데이터가 있으면 update를 해줌
	// save 함수는 id를 전달하면, id에 대한 데이터가 없으면 insert를 함 
	// email, password를 받을거임
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id : " +id;
	}
	
	@Transactional		// 이 어노테이션이 있으면 데이터가 update가 됨. 함수 종료시에 자동으로 변경을 감지하여 수정 및 commit이 됨.
	@PutMapping("/dummy/user/{id}")		// 정보 update는 PutMapping으로 해야함
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {		//json으로 받으려면 RequestBody 어노테이션이 필요함
		//json 데이터를 요청하면 Java Object(MessageConverter의 Jackson 라이브러리가)로 변환해서 받아줌
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email: "+requestUser.getEmail());
		
		// 자바는 파라미터에 함수를 못 넣음. 그래서 람다식을 써서 함수를 넣어줘야함
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail()); 
		
		//userRepository.save(user);		//update
		
		// 더티체킹이란? 복습 메모장에 상세하게 써놓음
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/user/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴받을예정
	@GetMapping("dummy/user")
	//Paging 전략 : id를 기준으로 2건씩 가져올거고, 최신순으로 가져올거임! 
	public List<User> pageList(@PageableDefault(size=2, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> paginUser = userRepository.findAll(pageable);
		
		List<User> users = paginUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {		// User 객체를 return 받을거임
		// findById가 Optional인 이유? user/4 주소(DB에 없는주소)를 찾았을때 내가 DB에서 못찾아오게 되면 user가 null이 될거임. 그럼 retrun이 null이 될거임. 그럼 프로그램에 오류가 생김
		// 그래서 Optional로 너의 User 객체를 감싸서 가져올거임. 그 때 null인지 아닌지 판단하고 return하면 됨!
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override //orElseThrow 이하 구문은 Optional이 발동되는 부분임. 없는 값을 DB에 요청했을때 빈 객체를 return하는 방법으로 Optional 처리해주는 부분임.
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 사용자가 없습니다. ");
			}
		}); 
		// 요청은 웹브라우저에서 했음
		// user 객체는 자바 오브젝트와 동일한 개념임
		// 웹브라우저는 html만 이해할 수 있음. 자바 오브젝트를 변환을 못함. 그래서 웹브라우저 이해할 수 있는 json 형태로 데이터를 변환해야함.
		// 스프링부트는 작동을 하게 되면 MEssageConverter가 자동으로 작동됨. 만약, 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출.
		// user 오브젝트를 json으로 변환해서 브라우저에게 전송함
		return user;	
		
		//****위 구문을 람다식으로 표현하면 더 쉬움. 근데 강사가 람다식은 어렵다고 해서 걍 나같은 초보는 위와 같이 타입 찾는 방식으로 하라고함.
		/*User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당사용자는 없습니다.");
		})
		return user;*/
	}

	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 요청하는거임
	@PostMapping("/dummy/join")
	public String join(User user) {
		//x-www.form.urlencoded 형식(key-value)으로 postman에서 보내면, 스프링프레임웍이 함수의 파라미터로 파싱해서 값을 나타냄
		System.out.println("id : "+user.getId());
		System.out.println("username : " +user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
}
