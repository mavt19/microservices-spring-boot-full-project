package com.bns.microservices.answers.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bns.microservices.answers.models.entity.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String>{

//	@Query("select a from Answer a join fetch a.student s join fetch a.question q join fetch q.exam e where s.id = ?1 and e.id = ?2")
//	@Query("select a from Answer a join fetch a.question q join fetch q.exam e where a.studentId = ?1 and e.id = ?2")
//	@Query("SELECT * FROM answers JOIN students ON answers.student_id = students.id JOIN questions ON answers.question_id = questions.id JOIN exams  ON questions.exam_id = exams.id  WHERE students.id = ?1 and exams.id = ?2")
	
//	public Iterable<Answer> findAnswerByStudentAndByExam(Long studentId, Long examId); 
	@Query("{ 'studentId' : ?0,  'questionId' : { $in: ?1 }}")
	public Iterable<Answer> findAnswerByStudentAndByQuestionIds(Long studentId, Iterable<Long> examIds); 
	
//	@Query("select a.id from Answer a join a.student s join a.question q join q.exam e where s.id = ?1 group by a.id")
//	@Query("select e.id from Answer a join a.question q join q.exam e where a.studentId = ?1 group by e.id")
	@Query("{ 'studentId' : ?0}")
	public Iterable<Answer> findAnswerIdWithAnswersByStudent(Long studentId); 
	
	@Query("{ 'studentId' : ?0, 'question.exam.id' : ?1 }")
	public Iterable<Answer> findAnswerByStudentIdAndExamId(Long studentId, Long examId); 
	
	@Query(value =  "{ 'studentId' : ?0}", fields = "{'question.exam.id' : 1}")
	public Iterable<Answer> findAnswerIdWithAnswersByStudentId(Long studentId); 
}
