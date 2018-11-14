package org.salem.persistence;

import org.salem.domain.FreeReply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<FreeReply, Long> {

}
