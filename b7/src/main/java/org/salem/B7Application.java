package org.salem;

import org.mybatis.spring.annotation.MapperScan;
import org.salem.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Setter;

@SpringBootApplication
@MapperScan(basePackages="org.salem.mapper")
public class B7Application {
	
	

	public static void main(String[] args) {
		SpringApplication.run(B7Application.class, args);
	}
}
