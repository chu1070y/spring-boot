package org.salem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
//자료구조에서 데이터를 검색할 때 fno만을 이용해서 equals와 hashcode를 따지는 것
@EqualsAndHashCode(of="fno")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	private String fname;
	private boolean current;
	
	// 프로필입장에서 프로필은 많고 멤버는 유니크 하므로 manytoone이다.
	@ManyToOne
	private Member member;
}
