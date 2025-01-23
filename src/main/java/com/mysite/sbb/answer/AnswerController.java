package com.mysite.sbb.answer;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	final QuestionService questionService;
	final AnswerService answerService;
	
	@PostMapping(value="/answer/create/{id}") //value= 는 생략해도 된다.
	String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content) {
		/*
		 * 템플릿(question_detail.html)에서 답변으로 입력한 내용(content)을 얻으려고 추가한 것이다.
		 * 템플릿의 답변 내용에 해당하는 <textarea>의 name 속성명이 content이므로 여기서도 변수명을 content로 사용한다.
		 */
		Question question = this.questionService.getQuestion(id); //getQuestion이 public으로 되어있어야 한다!
		this.answerService.create(question, content);
		return "redirect:/question/detail/"+id;
	}
}
