package org.salem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.salem.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class B7ApplicationTests {

	@Setter(onMethod_ = @Autowired)
	private TimeMapper mapper;

	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testTime() {
		log.info(mapper.getTime2());
	}

}
