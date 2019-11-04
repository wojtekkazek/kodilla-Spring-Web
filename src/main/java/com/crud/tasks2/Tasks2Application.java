package com.crud.tasks2;

import com.crud.tasks2.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.awt.*;

@SpringBootApplication
public class Tasks2Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		TaskDto taskDto = new TaskDto(
//				(long)1,
//				"Test title",
//				"I want to be a coder");
//
//		Long id = taskDto.getId();
//		String title = taskDto.getTitle();
//		String content = taskDto.getContent();
//
//		System.out.println(id + " " + title + " " + content);

		SpringApplication.run(Tasks2Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Tasks2Application.class);
	}

}
