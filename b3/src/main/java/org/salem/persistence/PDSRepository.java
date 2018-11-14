package org.salem.persistence;

import org.salem.domain.PDS;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PDSRepository extends CrudRepository<PDS, Long> {

	@Modifying
	@Query("update from PDSFile f set f.fname = ?2 "
			+ "where f.fno = ?1 ")
	public int updatePDSFiles(Long fno, String newFileName);
	
	@Modifying
	@Query("delete from PDSFile f where f.fno = ?1")
	public int deletePDSFile(Long fno);
}
