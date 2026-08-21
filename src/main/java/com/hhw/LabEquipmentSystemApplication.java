package com.hhw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hhw.mapper")
@SpringBootApplication
public class LabEquipmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabEquipmentSystemApplication.class, args);
	}

}
