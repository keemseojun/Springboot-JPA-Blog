<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>
			</ul>
		</div>
	</nav>
	<br>

	<div class="container">

		<div class="card m-2" >			<%-- div 자체는 블록 성질을 가지고 있어서 기본적으로 화면을 꽉 채우게 되어있음! --%>
			<div class="card-body">
				<h4 class="card-title">제목 설정 부분</h4>
				<p class="card-text">내용 설정 부분</p>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
	
	<div class="container">

		<div class="card m-2" >			<%-- div 자체는 블록 성질을 가지고 있어서 기본적으로 화면을 꽉 채우게 되어있음! --%>
			<div class="card-body">
				<h4 class="card-title">제목 설정 부분</h4>
				<p class="card-text">내용 설정 부분</p>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
	
	<div class="container">

		<div class="card m-2" >			<%-- div 자체는 블록 성질을 가지고 있어서 기본적으로 화면을 꽉 채우게 되어있음! --%>
			<div class="card-body">
				<h4 class="card-title">제목 설정 부분</h4>
				<p class="card-text">내용 설정 부분</p>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>Created by Seojun</p>
		<%--모든 홈페이지는 footer가 있어야함!! --%>
		<p>전화번호 : 010-1111-2222</p>
		<p>주소 : 서울시 강남구 논현동</p>
	</div>

</body>
</html>


