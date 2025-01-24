/*이제 질문 목록이 담긴 데이터를 조회하여 이를 템플릿을 통해 화면에 전달해 보려고 한다. 
질문 목록과 관련된 데이터를 조회하려면 QuestionRepository를 사용해야 한다. 
QuestionRepository로 조회한 질문 목록 데이터는 Model 클래스를 사용하여 템플릿에 전달할 수 있다.*/

package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question") //프리픽스(prefix)란 URL의 접두사 또는 시작 부분
@RequiredArgsConstructor //@RequiredArgsConstructor 애너테이션의 생성자 방식으로 questionRepository 객체를 주입했다.
@Controller
public class QuestionController {
	final QuestionService questionService;
	
	@GetMapping("/list")
	//@ResponseBody
	String list(Model model) { 
		/*매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
		Model 객체는 따로 생성할 필요 없이 컨트롤러의 메서드에 매개변수로 지정하기만 하면 스프링 부트가 자동으로 Model 객체를 생성한다.*/
		List<Question> questions = this.questionService.getList();
		model.addAttribute("questions", questions);
		/*Model 객체는 자바 클래스(Java class)와 템플릿(template) 간의 연결 고리 역할을 한다. 
		Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.*/
		return "question_list"; //question_list.html 템플릿 파일의 이름
	}
	
	@GetMapping(value = "/detail/{id}")
	String detail(Model model, @PathVariable("id") Integer id) { //변하는 id값을 얻을 때에는 @PathVariable 애너테이션을 사용한다.
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	String questionCreate(QuestionForm questionForm) {//question_create.html은 [질문 등록하기] 버튼을 통해 GET 방식으로 URL이 요청되더라도 th:object에 의해 QuestionForm 객체가 필요하다.
		return "question_create";
	}
	
	@PostMapping("/create")
	String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		//@Valid 애너테이션을 적용하면 QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작한다.
		//BindingResult 매개변수는 @Valid 애너테이션으로 검증이 수행된 결과를 의미하는 객체이다.
		//subject, content 항목을 지닌 폼이 전송되면 QuestionForm의 subject, content 속성이 자동으로 바인딩된다. 이렇게 이름이 동일하면 함께 연결되어 묶이는 것이 바로 폼의 바인딩 기능이다.
		if (bindingResult.hasErrors()) {
			return "question_create";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list"; //[저장하기] 버튼을 클릭해 질문이 저장되면 질문 목록 페이지로 이동
	}
}
//앞으로 작성할 다른 컨트롤러들도 이와 같이 리포지터리를 직접 사용하지 않고 컨트롤러 → 서비스 → 리포지터리 순서로 접근하는 과정을 거쳐 데이터를 처리할 것이다.