package com.bns.microservices.answers.services;

import com.bns.microservices.answers.models.entity.Answer;

public interface AnswerService {

	public Iterable<Answer> saveAll(Iterable<Answer> answers);
	
	public Iterable<Answer> findAnswerByStudentAndByExam(Long studentId, Long examId); 
	
	public Iterable<Answer> findAnswerByStudentAndByQuestionIds(Long studentId, Iterable<Long> examIds); 
	
	public Iterable<Long> findAnswerIdWithAnswersByStudent(Long studentId); 
	
	public Iterable<Answer> findAnswerByStudentId(Long studentId); 
	
	public Iterable<Answer> findAnswerByStudentIdAndExamId(Long studentId, Long examId);
	
	public Iterable<Long> findExamIdsWithAnswersByStudentId(Long studentId); 
}
