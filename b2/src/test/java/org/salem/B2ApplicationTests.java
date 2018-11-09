package org.salem;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Cleanup;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class B2ApplicationTests {

	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	
	@Test
	public void contextLoads() {
	}
	
	//@SneakyThrows(Exception.class) Exception을 던져준다.
	@Test
	@SneakyThrows(Exception.class)
	public void testConnection() {
		
		//@Cleanup이 auto close를 지원한다.
		@Cleanup Connection con = ds.getConnection();
		log.info(""+con);
	}

}
