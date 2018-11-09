package org.salem.persistence;

import org.salem.domain.MemberVO;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberVO, String>{
	
	
}
