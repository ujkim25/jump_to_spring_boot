package com.mysite.sbb.answer;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	final QuestionService questionService;
	final AnswerService answerService;
	
	@PostMapping(value="/answer/create/{id}") //value= 는 생략해도 된다.
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
		/*
		 * 템플릿(question_detail.html)에서 답변으로 입력한 내용(content)을 얻으려고 추가한 것이다.
		 * 템플릿의 답변 내용에 해당하는 <textarea>의 name 속성명이 content이므로 여기서도 변수명을 content로 사용한다.
		 */
		Question question = this.questionService.getQuestion(id); //getQuestion이 public으로 되어있어야 한다!
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question); //question_detail 템플릿은 Question 객체가 필요하므로 model 객체에 Question 객체를 저장한 후에 question_detail 템플릿을 출력해야 한다.
			return "question_detail";
		}
		this.answerService.create(question, answerForm.getContent());
		return "redirect:/question/detail/"+id;
	}
}
