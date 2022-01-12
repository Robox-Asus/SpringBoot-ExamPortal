package com.exam.controller;

import java.util.List;

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

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	//add quiz
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.addQuiz(quiz));
	}
	
	//udpate quiz
	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.updateQuiz(quiz));
	}
	
	// get all quiz
	@GetMapping("/")
	public ResponseEntity<?> getAllQuiz() {
		return ResponseEntity.ok(quizService.getQuizzes());
	}
	
	// get single quiz
	@GetMapping("/{qId}")
	public Quiz getQuiz(@PathVariable("qId")Long qId) {
		return quizService.getQuiz(qId);
	}
	
	//delete quiz
	@DeleteMapping("/{qId}")
	public void deleteQuiz(@PathVariable("qId")Long qId) {
		quizService.deleteQuiz(qId);
	}
	
	//get Question By Category
	@GetMapping("/category/{cid}")
	public ResponseEntity<?> getQuizzesByCategory(@PathVariable("cid")Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return ResponseEntity.ok(quizService.getQuizzesOfCategory(category));
	}
	
	//get Quiz By Ative
	@GetMapping("/active")
	public List<Quiz> getActiveOfQuizzes() {
		return this.quizService.findByActive();
	}
	
	// Get Quiz By Active and Category
	@GetMapping("/active/{cid}")
	public List<Quiz> getActiveQuizzesByCategory(@PathVariable("cid")Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.findByCategoryandActive(category);
	}
	
	
	
	
	
	
	
	
	
}
