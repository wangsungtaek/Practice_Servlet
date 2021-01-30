---
layout: post
title:  "servlet"
categories: JAVA
comments: true


---

<br>

## 쿠키생성 및 삭제

아래 예제는 쿠키 KEY 값으로 exp로 정의하고,

express 변수 안에 담긴 문자열 값으로 쿠키 객체를 생성함

쿠키 삭제 시에는 특정 조건이 만족하면 setMaxAge(0)을 통해 쿠키 객체를 삭제한다.

~~~java
// 쿠키 객체 생성
Cookie expressCookie = new Cookie("exp", express);

// 쿠키 삭제 ( 계산기에서 C버튼 누르면 )
if(inOper != null && inOper.equals("C")) expressCookie.setMaxAge(0);
~~~
___

<br>

<br>

## 서버내 페이지 이동

~~~java
response.sendRedirect("calcpage");
~~~

<br>

<br>

<br>

유튜브 '뉴렉처'을 듣고 정리 =)
