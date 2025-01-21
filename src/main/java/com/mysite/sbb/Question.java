package com.mysite.sbb;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*일반적으로 엔티티를 만들 때에는 Setter 메서드를 사용하지 않기를 권한다. 
왜냐하면 엔티티는 데이터베이스와 바로 연결되므로 데이터를 자유롭게 변경할 수 있는 Setter 메서드를 허용하는 것이 안전하지 않다고 판단하기 때문이다.
엔티티는 생성자에 의해서만 엔티티의 값을 저장할 수 있게 하고, 데이터를 변경해야 할 경우에는 메서드를 추가로 작성하면 된다.*/
@Entity
public class Question {
	//엔티티의 속성은 테이블의 열 이름과 일치
	@Id //기본키로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 속성만 번호가 자동으로 1씩 증가하여 저장된다.
	int id;
	
	@Column(length=200)
	String subject;
	
	@Column(columnDefinition="TEXT") //글자 수를 제한할 수 없는 경우
	String content;
	
	//엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블의 열로 인식한다.
	LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //mappedBy는 참조 엔티티의 속성명을 정의
	List<Answer> answerList;
}
