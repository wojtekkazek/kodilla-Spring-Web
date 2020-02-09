package com.crud.tasks2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//tomcat
public class Tasks2Application extends SpringBootServletInitializer {
    //heroku
//public class Tasks2Application {

	public static void main(String[] args) {
// 18.3
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

//	Tomcat
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Tasks2Application.class);
//	}

}
