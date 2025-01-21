package com.mysite.sbb;

import lombok.Getter;
//import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
//@Setter
@RequiredArgsConstructor
public class HelloLombok {
	private final String hello; //Lombok은 속성 이름을 기준으로 메서드 이름을 자동 생성
	private final int lombok; //final이 없다면 생성자에 포함되지 않는다.
	//final을 적용하면 속성값을 변경할 수 없기 때문에 @Setter는 의미가 없어지고, Setter 메서드 또한 사용할수 없다.
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("헬로", 5);
		//helloLombok.setHello("헬로");
		//helloLombok.setLombok(5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}
}
