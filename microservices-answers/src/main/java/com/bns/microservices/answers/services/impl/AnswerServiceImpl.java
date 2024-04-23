package com.bns.microservices.answers.services.impl;


import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bns.microservices.answers.clients.ExamFeignClient;
import com.bns.microservices.answers.models.entity.Answer;
import com.bns.microservices.answers.models.entity.Exam;
import com.bns.microservices.answers.models.entity.Question;
import com.bns.microservices.answers.models.repository.AnswerRepository;
import com.bns.microservices.answers.services.AnswerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService{

	private final AnswerRepository answerRepository;
	
	private final ExamFeignClient examFeignClient;
	
	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		// TODO Auto-generated method stub
		return answerRepository.saveAll(answers);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentAndByExam(Long studentId, Long examId) {
		// TODO Auto-generated method stub
//		return answerRepository.findAnswerByStudentAndByExam(studentId, examId);
		Exam exam = examFeignClient.getExamById(examId);
		List<Question> questions = exam.getQuestions();
		List<Long> questionIds = questions.stream().map(Question::getId).toList();
		List<Answer> answers = (List<Answer>) this.findAnswerByStudentAndByQuestionIds(studentId, questionIds);
		answers = answers.stream().map(a->{
			questions.forEach(x->{
				if(x.getId() == a.getQuestionId()) {
					a.setQuestion(x);
				}
			});
			return a;
		}).toList();
		return answers;
	}

	@Override
	public Iterable<Long> findAnswerIdWithAnswersByStudent(Long studentId) {
		// TODO Auto-generated method stub
//		return answerRepository.findAnswerIdWithAnswersByStudent(studentId);
		List<Answer> answersStudent = (List<Answer>) answerRepository.findAnswerIdWithAnswersByStudent(studentId);
		List<Long> examnIds = Collections.emptyList();
		if(!answersStudent.isEmpty()) {
			List<Long> questionsIds = answersStudent.stream().map(Answer::getQuestionId).toList();
			examnIds = examFeignClient.answeredByQuestionsIds(questionsIds);
		}
		
		return examnIds;
	}

	@Override
	public Iterable<Answer> findAnswerByStudentAndByQuestionIds(Long studentId, Iterable<Long> examIds) {
		// TODO Auto-generated method stub
		return answerRepository.findAnswerByStudentAndByQuestionIds(studentId, examIds);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return answerRepository.findAnswerIdWithAnswersByStudent(studentId);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentIdAndExamId(Long studentId, Long examId) {
		// TODO Auto-generated method stub
		return answerRepository.findAnswerByStudentIdAndExamId(studentId, examId);
	}

	@Override
	public Iterable<Long> findExamIdsWithAnswersByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		List<Answer> answersStudent = (List<Answer>) answerRepository.findAnswerIdWithAnswersByStudentId(studentId);
		return answersStudent.stream().map(r-> r.getQuestion().getExam().getId()).distinct().toList();
	}

}
