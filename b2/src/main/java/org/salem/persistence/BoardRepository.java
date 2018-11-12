package org.salem.persistence;

import org.salem.domain.BoardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends CrudRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO>{

	//11월 12일
	@Query("select b from BoardVO b where bno > 0 order by b.bno desc")
	public Page<BoardVO> getList(Pageable pageable);
	
	//파라미터를 넣을 때 콜론을 꼭 써줘야한다.
	@Query("select b from BoardVO b where b.title like %:title% and bno > 0 order by b.bno desc")
	public Page<BoardVO> getListByTitle(@Param("title") String title, Pageable pageable);
	
	@Query("select b from BoardVO b where b.content like %:content% and bno > 0 order by b.bno desc")
	public Page<BoardVO> getListByContent(@Param("content") String content, Pageable pageable);
	
	@Query("select b from BoardVO b where b.writer like %:writer% and bno > 0 order by b.bno desc")
	public Page<BoardVO> getListByWriter(@Param("writer") String writer, Pageable pageable);

//	@Query("select b from BoardVO b where b.bno > 0")
//	public Page<BoardVO> getList(Pageable pageable);

	
	
//	//11월 09일
//	
//	//앞으로 게시판 만들때 무조건 Page타입을 쓰십시오. 그러면 다 해결됩니다.
//	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
//	
////	public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
//	
//	public List<BoardVO> findByTitleContainingOrderByBnoDesc(String keyword);
//	
//	public List<BoardVO> findByTitleContaining(String keyword, Pageable pageable);
//	
//	//인덱스타야해서 이렇게 주는게 낫죠!
//	//항상 이런 습관을 가지도록 하세요!!
//	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword, Long bno, Pageable pageable);
//	
}
