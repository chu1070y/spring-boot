package org.salem.persistence;

import org.salem.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String>{

	
}
