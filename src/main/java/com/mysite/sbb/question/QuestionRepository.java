package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	// Question 엔티티로 리포지터리를 생성한다는 의미. Question 엔티티의 기본키가 Integer임을 이와 같이 추가로 지정
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String s1, String s2);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
}
