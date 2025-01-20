package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	@GetMapping("/sbb")
	
	@ResponseBody() //URL 요청에 대한 응답으로 문자열을 리턴하라는 의미
	public String greeting() {
		return "안녕하세요 sbb에 오신 것을 환영합니다."; //URL과 매핑된 메서드는 결괏값을 리턴해야 한다
	}
}
