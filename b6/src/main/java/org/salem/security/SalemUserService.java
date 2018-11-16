package org.salem.security;

import org.salem.domain.Member;
import org.salem.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class SalemUserService implements UserDetailsService{
	
	//db와 연결할 수 있게 repository 주입
	@Setter(onMethod_ = @Autowired)
	private MemberRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warning("=====================");
		log.warning("=====================");
		log.warning("loadUser" + username);
		log.warning("=====================");
		log.warning("=====================");
		
		return repo.findById(username)
				.filter(vo -> vo != null)
				.map(vo -> new SalemSecurityUser(vo))
				.get();
	}
	
	
	
}
