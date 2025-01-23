package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	final QuestionRepository questionRepository;
	
	List<Question> getList() {
		/*List<Question> lq = this.questionRepository.findAll();
		return lq;*/
		return this.questionRepository.findAll();
	}
}
