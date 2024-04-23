package com.bns.microservices.answers.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bns.microservices.answers.models.entity.Exam;

@FeignClient(name = "microservices-exams")
public interface ExamFeignClient {

	@GetMapping("/{id}")
	public Exam getExamById(@PathVariable("id") Long id);
	
	@GetMapping("/answered-by-questions")
	public List<Long> answeredByQuestionsIds(@RequestParam("questionsIds") List<Long> questionsIds);
}
