package com.exam.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService	{
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		if (question.getQuesId() == null)
			return questionRepository.save(question);
		return null;
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		if(questionRepository.findById(question.getQuesId()).get() != null)
			return questionRepository.save(question);
		return null;
	}

	@Override
	public Set<Question> getQuestion() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long quesId) {
		// TODO Auto-generated method stub
		return questionRepository.findById(quesId).get();
	}

	@Override
	public Set<Question> getQuestionByQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(quesId);
	}

	
}
