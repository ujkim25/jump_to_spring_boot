/*이제 질문 목록이 담긴 데이터를 조회하여 이를 템플릿을 통해 화면에 전달해 보려고 한다. 
질문 목록과 관련된 데이터를 조회하려면 QuestionRepository를 사용해야 한다. 
QuestionRepository로 조회한 질문 목록 데이터는 Model 클래스를 사용하여 템플릿에 전달할 수 있다.*/

package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	String questionCreate() {
		return "question_create";
	}
	
	@PostMapping("/create")
	String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list"; //[저장하기] 버튼을 클릭해 질문이 저장되면 질문 목록 페이지로 이동
	}
}
//앞으로 작성할 다른 컨트롤러들도 이와 같이 리포지터리를 직접 사용하지 않고 컨트롤러 → 서비스 → 리포지터리 순서로 접근하는 과정을 거쳐 데이터를 처리할 것이다.