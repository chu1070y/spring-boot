package org.salem.persistence;

import java.util.List;

import org.salem.domain.FreeBoard;
import org.salem.domain.FreeReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<FreeReply, Long> {

	@Query("select r from FreeReply r where r.board = ?1 order by r.rno asc")
	public List<FreeReply> getListByBoard(FreeBoard board);
	
}
