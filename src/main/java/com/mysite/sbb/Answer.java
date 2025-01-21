package com.mysite.sbb;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne //질문 엔티티와 연결된 속성이라는 것을 답변 엔티티에 표시해야 한다.
	//실제 데이터베이스에서는 외래키(foreign key) 관계가 생성된다.
	Question question;
	
	@Column(columnDefinition = "TEXT")
	String content;
	
	LocalDateTime createDate;
}
