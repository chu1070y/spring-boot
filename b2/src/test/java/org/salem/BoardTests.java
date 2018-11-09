package org.salem;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.salem.domain.BoardVO;
import org.salem.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;
	
	@Test
	public void testFind4() {
		
		//0은 페이지 번호, 5은 5개씩
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC,"bno");
		
		//메소드이름에 따라 쿼리문 실행이 된다.
		boardRepository.findByTitleContainingAndBnoGreaterThan("7",0L,pageable).forEach(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testFind3() {
		
		//0은 페이지 번호, 10은 10개씩
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC,"bno");
		
		//메소드이름에 따라 쿼리문 실행이 된다.
		boardRepository.findByTitleContaining("7",pageable).forEach(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testFind2() {
		//메소드이름에 따라 쿼리문 실행이 된다.
		boardRepository.findByTitleContainingOrderByBnoDesc("2").forEach(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testFind1() {
		
		//0은 페이지 번호, 10은 10개씩
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		//메소드이름에 따라 쿼리문 실행이 된다.
		Page<BoardVO> result = boardRepository.findByBnoGreaterThan(0L,pageable);
		
		//이렇게 하면 select 쿼리가 두개 날라간다.
		//우리가 게시판할때 필요한 count쿼리까지 날라간다.
		log.info(""+result);
		
		log.info("Total pages: "+result.getTotalPages());
		log.info("Page: "+result.getNumber());
		log.info("Next: "+result.hasNext());
		log.info("Prev: "+result.hasPrevious());
		
		//
		log.info("P Next"+result.nextPageable());
		log.info("P Prev"+result.previousPageable());
		
		//이건 실제 데이터
		result.getContent().forEach(vo -> log.info("" + vo));
	}
	
	@Test
	public void testDelete() {
		
		boardRepository.deleteById(10L);
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO vo = new BoardVO();
		vo.setBno(10L);
		vo.setTitle("제목 10 수정");
		vo.setContent("내용 10 수정");
		vo.setWriter("user10");
		
		boardRepository.save(vo);
		
		
//		
//		//10번글 수정하기
//		boardRepository.findById(10L).ifPresent(vo -> {
//			
//			vo.setContent("수정된 제목입니다.");
//			
//			boardRepository.save(vo);
//			
//		});
		
	}
	
	@Test
	public void testRead() {
		
		Optional<BoardVO> result = boardRepository.findById(10L);
		
		//nullpointerException 없애주는 것
		result.ifPresent(vo -> log.info(""+vo));
		
		//코드를 줄일 경우
//		boardRepository.findById(10L).ifPresent(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testInsert() {
		
		IntStream.range(0, 100).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("게시물" + i);
			vo.setContent("내용"+i);
			vo.setWriter("user"+(i%10) );
			
			boardRepository.save(vo);
		});
	}
}
