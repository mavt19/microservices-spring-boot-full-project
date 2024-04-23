package com.bns.microservices.answers.models.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastname;
	@NotEmpty
	@Email
	private String email;

}
