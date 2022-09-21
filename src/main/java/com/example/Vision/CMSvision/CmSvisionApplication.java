package com.example.Vision.CMSvision;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CmSvisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmSvisionApplication.class, args);
		System.out.println("Successfully Started");
	}
	@Bean
	public ModelMapper modelMapper(){
		 return new ModelMapper();
	}

}
