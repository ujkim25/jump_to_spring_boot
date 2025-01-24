package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.answer.Answer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	final QuestionRepository questionRepository;
	
	public List<Question> getList() {
		/*List<Question> lq = this.questionRepository.findAll();
		return lq;*/
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(int id){
		Optional<Question> oq = this.questionRepository.findById(id);
		if(oq.isPresent()) {
			return oq.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());		
		this.questionRepository.save(question);
	}
}
