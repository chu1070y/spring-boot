package org.salem.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.salem.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class SalemSecurityUser extends User {
	
	private Member member;
	
	public SalemSecurityUser(Member member) {
		
		//타입을 전혀 다른걸로 바꿔주는 걸 mapping이라 한다. 여기서 map이 이런 역할을 한다.
		super(member.getMid(), member.getMpw(),
				member.getAuthList().stream()
					.map(auth -> new SimpleGrantedAuthority("ROLE_"+auth.getAuth()))
					.collect(Collectors.toList())
		);
		
		// html에 [[${#authentication.principal}]] 나오게 하는 코드
		this.member = member;
		
	}
	
	
}
