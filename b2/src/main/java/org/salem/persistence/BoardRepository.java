package org.salem.persistence;

import java.util.List;

import org.salem.domain.BoardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<BoardVO, Long>{

	//앞으로 게시판 만들때 무조건 Page타입을 쓰십시오. 그러면 다 해결됩니다.
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
	
//	public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	public List<BoardVO> findByTitleContainingOrderByBnoDesc(String keyword);
	
	public List<BoardVO> findByTitleContaining(String keyword, Pageable pageable);
	
	//인덱스타야해서 이렇게 주는게 낫죠!
	//항상 이런 습관을 가지도록 하세요!!
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword, Long bno, Pageable pageable);
	
}
