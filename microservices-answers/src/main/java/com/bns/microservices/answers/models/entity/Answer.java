package com.bns.microservices.answers.models.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection  = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Answer {

	@Id
	private String id;
	@NotEmpty
	private String text;
//	@Transient
	private Student student;
	
	private Long studentId;
	
//	@Transient
	private Question question;
	
	private Long questionId;
}
