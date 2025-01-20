//웹 프로그램 개발을 도와주는 스프링 부트의 도구(라이브러리). 개발 시 자주 사용하는 코드를 모아 둔 것

package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //annotation 이란 자바의 클래스, 메서드, 변수 등에 정보를 부여하여 부가 동작을 할 수 있게 하는 목적으로 사용된다.
public class HelloController {
	@GetMapping("/hello")
	//http://localhost:8080/hello URL 요청이 발생하면 hello 메서드가 실행됨을 의미한다. 즉, /hello URL과 hello 메서드를 매핑하는 역할을 한다.
	@ResponseBody
	public String hello() {
		return "Hello SBB";
	}
}
