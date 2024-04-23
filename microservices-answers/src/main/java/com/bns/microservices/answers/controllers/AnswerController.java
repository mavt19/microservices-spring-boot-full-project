package com.bns.microservices.answers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bns.microservices.answers.models.entity.Answer;
import com.bns.microservices.answers.services.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AnswerController {

	private final AnswerService answerService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Iterable<Answer> answers){
		List<Answer>listAnswers = (List<Answer>) answers;
		listAnswers.stream()
		.map(a -> {
			a.setStudentId(a.getStudent().getId());
			a.setQuestionId(a.getQuestion().getId());
			return a;
		})
		.toList();
		Iterable<Answer> answersDB = answerService.saveAll(listAnswers);
		System.out.println(listAnswers); 
		return ResponseEntity.status(HttpStatus.CREATED).body(answersDB);
	}
	
	@GetMapping("/student/{studentId}/exam/{examId}")
	public ResponseEntity<?> getAnswerByStudentAndByExam(@PathVariable("studentId") Long studentId, @PathVariable("examId") Long examId){
//		Iterable<Answer> answersListDb = answerService.findAnswerByStudentAndByExam(studentId, examId);
		Iterable<Answer> answersListDb = answerService.findAnswerByStudentIdAndExamId(studentId, examId);
		return ResponseEntity.ok(answersListDb);
	}
	
	@GetMapping("/student/{studentId}/exams-answered")
	public ResponseEntity<?> getAnswerIdWithAnswersByStudent(@PathVariable("studentId") Long studentId){
//		List<Long> answersIdListDb = (List<Long>) answerService.findAnswerIdWithAnswersByStudent(studentId);
		List<Long> answersIdListDb = (List<Long>) answerService.findExamIdsWithAnswersByStudentId(studentId);
		return ResponseEntity.ok(answersIdListDb);
	}
}
