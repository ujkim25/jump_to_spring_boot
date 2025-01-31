package com.mysite.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> { //SiteUser의 기본키 타입은 Long 이기 때문에 이렇게 해줌
	
}
