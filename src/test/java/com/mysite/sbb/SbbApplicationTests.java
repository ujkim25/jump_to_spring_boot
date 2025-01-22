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
		Question question = questionRepository.findBySubject("sbb가 무엇인가요?");
		/*JPA에 리포지터리의 메서드명을 분석하여 쿼리를 만들고 실행하는 기능이 있기 때문에 가능하다.
		 즉, 여러분은 findBy + 엔티티의 속성명(예를 들어 findBySubject)과 같은 리포지터리의 메서드를 작성하면 입력한 속성의 값으로 데이터를 조회할 수 있다!*/
		assertEquals(1, question.getId());
	}
}
