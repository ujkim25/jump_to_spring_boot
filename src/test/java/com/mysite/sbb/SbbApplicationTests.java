package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	@Autowired // 스프링의 의존성 주입(DI) : 스프링이 객체를 대신 생성하여 주입하는 기법
	/*객체를 주입하는 방식에는 @Autowired 애너테이션을 사용하는 것 외에 Setter 메서드 또는 생성자를 사용하는 방식이 있다.
	 개발 시 @Autowired보다는 생성자를 통한 객체 주입 방식을 권장한다.*/
	QuestionRepository questionRepository;
	
	@Test
	void testJpa() {
		Question question = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1,question.getId());
	}
}
