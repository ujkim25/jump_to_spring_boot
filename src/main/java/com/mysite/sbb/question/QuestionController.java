/*이제 질문 목록이 담긴 데이터를 조회하여 이를 템플릿을 통해 화면에 전달해 보려고 한다. 
질문 목록과 관련된 데이터를 조회하려면 QuestionRepository를 사용해야 한다. 
QuestionRepository로 조회한 질문 목록 데이터는 Model 클래스를 사용하여 템플릿에 전달할 수 있다.*/

package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //@RequiredArgsConstructor 애너테이션의 생성자 방식으로 questionRepository 객체를 주입했다.
@Controller
public class QuestionController {
	final QuestionRepository questionRepositry;
	
	@GetMapping("/question/list")
	//@ResponseBody
	String list(Model model) { 
		/*매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
		Model 객체는 따로 생성할 필요 없이 컨트롤러의 메서드에 매개변수로 지정하기만 하면 스프링 부트가 자동으로 Model 객체를 생성한다.*/
		List<Question> questions = this.questionRepositry.findAll();
		model.addAttribute("questions", questions);
		/*Model 객체는 자바 클래스(Java class)와 템플릿(template) 간의 연결 고리 역할을 한다. 
		Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.*/
		return "question_list"; //question_list.html 템플릿 파일의 이름
	}
}
