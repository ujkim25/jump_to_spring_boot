package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	@GetMapping("/sbb")
	
	@ResponseBody() //URL 요청에 대한 응답으로 문자열을 리턴하라는 의미. 컨트롤러 메서드의 반환값을 HTTP 응답 본문(body)으로 직접 전송하도록 지정.
	public String greeting() {
		return "안녕하세요 sbb에 오신 것을 환영합니다."; //URL과 매핑된 메서드는 결괏값을 리턴해야 한다
	}
	
	@GetMapping("/")
	//@ResponseBody()가 없으면 스프링은 반환값을 뷰 이름 또는 리다이렉트 명령으로 해석한다.
	String root(){
		return "redirect:/question/list";
	}
	//리턴 문자열 redirect:/question/list는 /question/list URL로 페이지를 리다이렉트하라는 명령어이다.
}
