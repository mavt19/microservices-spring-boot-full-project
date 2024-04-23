package com.bns.microservices.answers.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	private Long id;
	
	private String text;
	
	@JsonIgnoreProperties(value = {"questions"})
	private Exam exam;
}
