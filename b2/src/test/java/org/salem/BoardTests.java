package org.salem;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.salem.domain.BoardVO;
import org.salem.domain.QBoardVO;
import org.salem.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;
	
	@Test
	public void testDynamic() {
		
//		String type = "t";
		String[] types = {"t","c"};
		String keyword = "10";
		
		//where조건에 맞는지 안맞는지 보는것
		//where 조건에만 해당되어서 order by는 없다.
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoardVO board = QBoardVO.boardVO;
		
		BooleanExpression[] arr = new BooleanExpression[types.length];
		
		for (int i = 0 ; i < types.length; i++) {
			
			String type = types[i];
			
			BooleanExpression cond = null;
			
			if(type.equals("t")) {
			
				cond = board.title.contains(keyword);
			}
			
			if(type.equals("c")) {
			
				cond = board.content.contains(keyword);
			}
			
			arr[i] = cond;
			
		}//end for
		
		builder.andAnyOf(arr);
		
		builder.and(board.bno.gt(0));
		
		Page<BoardVO> result = boardRepository.findAll(builder, PageRequest.of(0, 10, Sort.Direction.DESC,"bno"));
		
		log.info(""+result);
		
	}
	
	
	@Test
	public void testTitle() {
		
		Page<BoardVO> result = boardRepository.getListByTitle("10",PageRequest.of(0, 10));
		log.info("=========================");
		log.info(""+result);
		log.info("=========================");
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testContent() {
		
		Page<BoardVO> result = boardRepository.getListByContent("10",PageRequest.of(0, 10));
		log.info("=========================");
		log.info(""+result);
		log.info("=========================");
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testWriter() {
		
		Page<BoardVO> result = boardRepository.getListByWriter("9",PageRequest.of(0, 10));
		log.info("=========================");
		log.info(""+result);
		log.info("=========================");
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testList() {
		
		Page<BoardVO> result = boardRepository.getList(PageRequest.of(0, 10));
		
		log.info(""+result);
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
		
		IntStream.range(100, 1000).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("추게시물" + i);
			vo.setContent("추내용"+i);
			vo.setWriter("user"+(i%10) );
			
			boardRepository.save(vo);
		});
	}
}
