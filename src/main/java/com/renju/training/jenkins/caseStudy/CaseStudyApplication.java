package com.renju.training.jenkins.caseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.renju.training.jenkins.caseStudy")
public class CaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}
}
