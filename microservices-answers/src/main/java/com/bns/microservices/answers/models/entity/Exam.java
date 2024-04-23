package com.bns.microservices.answers.models.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

	private Long id;
	@NotEmpty
	private String name;
	

    private OffsetDateTime createAt;
	

	@JsonIgnoreProperties(value = {"exam"}, allowSetters = true)
	private List<Question> questions = new ArrayList<>();
	

	private Course course;

	private boolean answered;
}
