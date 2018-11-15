package org.salem.controller;

import java.util.List;

import org.salem.domain.FreeBoard;
import org.salem.domain.FreeReply;
import org.salem.persistence.ReplyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
@AllArgsConstructor
public class ReplyController {
	
	private ReplyRepository repository;
	
	//repository를 호출하지 않아도 알아서 select문을 날리고 bno가 300인 board를 가져온다.
	@GetMapping("/{bno}")
	public ResponseEntity<List<FreeReply>> getList(@PathVariable("bno") Long bno){
		
		log.info(""+bno);
		
		FreeBoard board = new FreeBoard();
		board.setBno(bno);
		
		List<FreeReply> list = repository.getListByBoard(board);
		
		list.forEach(reply -> {
			log.info("" + reply);
		});
		
		return new ResponseEntity<List<FreeReply>>(list, HttpStatus.OK);
	}
	
}
