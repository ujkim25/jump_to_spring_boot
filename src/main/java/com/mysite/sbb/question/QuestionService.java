package com.mysite.sbb.question;

//import 문은 Ctrl + Shift + O 키를 누르면 한번에 정리됨
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	final QuestionRepository questionRepository;
	
	public Page<Question> getList(int page) {
		/*List<Question> lq = this.questionRepository.findAll();
		return lq;*/
		//return this.questionRepository.findAll();
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate")); //Entity(Question)에 존재하는 필드를 정렬 기준으로 사용
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); //page는 조회할 페이지의 번호이고, 10은 한 페이지에 보여 줄 게시물의 개수. 데이터 전체를 조회하지 않고 해당 페이지의 데이터만 조회
		//게시물을 역순(최신순)으로 조회하려면 이와 같이 PageRequest.of 메서드의 세 번째 매개변수에 Sort 객체를 전달해야 한다.
		return this.questionRepository.findAll(pageable);
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
