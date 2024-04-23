package com.bns.microservices.answers.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	private Long id;
	
	private String name;
	
	@JsonIgnoreProperties(value = {"children"}, allowSetters = true)
	private Course father;
	
	@JsonIgnoreProperties(value = {"father"}, allowSetters = true)
	private List<Course> children = new ArrayList<>();
}
