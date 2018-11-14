package org.salem.persistence;

import java.util.List;

import org.salem.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

	@Query("select m.uid, count(p) from Member m left outer join Profile p "
			+ "on m.uid = p.member where m.uid = ?1 group by m")
	public List<Object[]> getMemberWithProfileCount(String uid);
	
	
}
