package org.salem.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//테이블을 주지않으면 클래스이름으로 테이블 이름이 설정된다.
@Entity
@Data
public class Member {
	
	
	@Id
	private String uid;
	private String upw;
	private String uname;
}
