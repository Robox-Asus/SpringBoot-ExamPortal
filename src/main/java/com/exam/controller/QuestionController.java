package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add question
	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.addQuestion(question));
	}

	// update Question
	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}

	// get all question
	@GetMapping("/")
	public Set<Question> getAllQuestion() {
		return new LinkedHashSet<>(questionService.getQuestion());
	}

	// get single question
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long quesId) {
		return questionService.getQuestion(quesId);
	}

	// delete Question
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long quesId) {
		questionService.deleteQuestion(quesId);
	}

	// get Question By Quiz
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionByQuiz(@PathVariable("qId") Long qId) {
//		Quiz q = new Quiz();
//		q.setqId(qId);
//		return ResponseEntity.ok(this.questionService.getQuestionByQuiz(q));

		Quiz quiz = this.quizService.getQuiz(qId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<Question>(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	// get all Question By Quiz Admin
	@GetMapping("/quiz/all/{qId}")
	public ResponseEntity<?> getQuestionByQuizAdmin(@PathVariable("qId") Long qId) {
//			Quiz q = new Quiz();
//			q.setqId(qId);
//			return ResponseEntity.ok(this.questionService.getQuestionByQuiz(q));

		Quiz quiz = this.quizService.getQuiz(qId);
		Set<Question> questions = quiz.getQuestions();
		return ResponseEntity.ok(questions);
	}

	// eval - quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		Double marksGot = 0.0;
		Integer correctAnswers = 0;
		Integer attempted = 0;
		for (Question ques : questions) {
			Question question = this.questionService.getQuestion(ques.getQuesId());
			if (question.getAnswer().equals(ques.getGivenAnswer())) {
				correctAnswers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;
			}

			if (ques.getGivenAnswer() != null)
				attempted++;
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("marksGot", marksGot);
		map.put("correctAnswers", correctAnswers);
		map.put("attempted", attempted);
		
		return ResponseEntity.ok(map);
	}

}
