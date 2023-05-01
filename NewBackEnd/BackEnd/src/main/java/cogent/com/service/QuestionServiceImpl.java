package cogent.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;
import cogent.com.entity.Answer;
import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public QuestionDTO addQuestion(QuestionDTO questionDTO) {
		return convertQuestionToQuestionDTO(questionRepository.save(convertQuestionDTOToQuestion(questionDTO)));
	}

	@Override
	public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
		return convertQuestionToQuestionDTO(questionRepository.save(convertQuestionDTOToQuestion(questionDTO)));
	}

	@Override
	public void deleteQuestionById(int id) {
		questionRepository.deleteById(id);
	}

	@Override
	public List<QuestionDTO> getAllQuestion() {
		List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		questionRepository.findAll().forEach(question -> {
			questionDTOs.add(convertQuestionToQuestionDTO(question));
		});
		return questionDTOs;
	}

	@Override
	public List<QuestionDTO> getQuestionByTopic(String topic) {
		List<QuestionDTO> questionDTOs = new ArrayList<>();
		questionRepository.findByTopic(topic).forEach(question -> {
			questionDTOs.add(convertQuestionToQuestionDTO(question));
		});
		return questionDTOs;
	}

	@Override
	public Optional<QuestionDTO> getQuestionById(int id) {
		return convertQuestionToQuestionDTO(questionRepository.findById(id));
	}

	@Override
	public List<QuestionDTO> getAllQuestionsByStatus(@PathVariable boolean status) {
		List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		questionRepository.findByStatus(status).forEach(question -> {
			questionDTOs.add(convertQuestionToQuestionDTO(question));
		});
		return questionDTOs;
	}

	@Override
	public List<QuestionDTO> getQuestionByTitle(String title) {
		List<QuestionDTO> allQuestionDTOs = new ArrayList<QuestionDTO>();
		questionRepository.findAll().forEach(question -> {
			allQuestionDTOs.add(convertQuestionToQuestionDTO(question));
		});
		List<QuestionDTO> allQuestionsByTitle = new ArrayList<QuestionDTO>();
		for (QuestionDTO question : allQuestionsByTitle) {
			if (question.getTitle().contains(title))
				allQuestionsByTitle.add(question);
		}
		return allQuestionsByTitle;
	}

	@Override
	public QuestionDTO addAnswerToQuestion(int question_id, AnswerDTO answerDTO) {
		QuestionDTO questionDTO = convertQuestionToQuestionDTO(questionRepository.findById(question_id).get());
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		questionDTO.getAnswers().forEach(answer -> {
			answersDTO.add(convertAnswerToAnswerDTO(answer));
		});
		answersDTO.add(answerDTO);
		List<Answer> answers = new ArrayList<Answer>();
		for (AnswerDTO answer : answersDTO) {
			answers.add(convertAnswerDTOToAnswer(answer));
		}
		questionDTO.setAnswers(answers);
		QuestionDTO updatedQuestion = this.updateQuestion(questionDTO);
		Question newUpdatedQuestion = questionRepository.save(convertQuestionDTOToQuestion(updatedQuestion));
		return convertQuestionToQuestionDTO(newUpdatedQuestion);
	}

	QuestionDTO convertQuestionToQuestionDTO(Question question) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(question.getId());
		questionDTO.setTopic(question.getTopic());
		questionDTO.setAnswers(question.getAnswers());
		questionDTO.setDatetime(question.getDatetime());
		questionDTO.setDescriptionQuestion(question.getDescriptionQuestion());
		questionDTO.setImageSrc(question.getImageSrc());
		questionDTO.setQapproved_by(question.getQapproved_by());
		questionDTO.setQcreated_by(question.getQcreated_by());
		questionDTO.setStatus(question.getStatus());
		questionDTO.setTitle(question.getTitle());
		return questionDTO;
	}

	Optional<QuestionDTO> convertQuestionToQuestionDTO(Optional<Question> question) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(question.get().getId());
		questionDTO.setTopic(question.get().getTopic());
		questionDTO.setAnswers(question.get().getAnswers());
		questionDTO.setDatetime(question.get().getDatetime());
		questionDTO.setDescriptionQuestion(question.get().getDescriptionQuestion());
		questionDTO.setImageSrc(question.get().getImageSrc());
		questionDTO.setQapproved_by(question.get().getQapproved_by());
		questionDTO.setQcreated_by(question.get().getQcreated_by());
		questionDTO.setStatus(question.get().getStatus());
		questionDTO.setTitle(question.get().getTitle());
		return Optional.of(questionDTO);
	}

	AnswerDTO convertAnswerToAnswerDTO(Answer answer) {
		AnswerDTO answerDTO = new AnswerDTO();
		answerDTO.setId(answer.getId());
		answerDTO.setDescription_answer(answer.getDescription_answer());
		answerDTO.setDatetime(answer.getDatetime());
		answerDTO.setImg_src(answer.getImg_src());
		answerDTO.setQuestion(answer.getQuestion());
		answerDTO.setApproved_by(answer.getApproved_by());
		answerDTO.setCreated_by(answer.getCreated_by());
		answerDTO.setStatus(answer.getStatus());
		return answerDTO;
	}

	Question convertQuestionDTOToQuestion(QuestionDTO questionDTO) {
		Question question = new Question();
		question.setId(questionDTO.getId());
		question.setTopic(questionDTO.getTopic());
		question.setAnswers(questionDTO.getAnswers());
		question.setDatetime(questionDTO.getDatetime());
		question.setDescriptionQuestion(questionDTO.getDescriptionQuestion());
		question.setImageSrc(questionDTO.getImageSrc());
		question.setQapproved_by(questionDTO.getQapproved_by());
		question.setQcreated_by(questionDTO.getQcreated_by());
		question.setStatus(questionDTO.isStatus());
		question.setTitle(questionDTO.getTitle());
		return question;
	}

	Answer convertAnswerDTOToAnswer(AnswerDTO answerDTO) {
		Answer answer = new Answer();
		answer.setId(answerDTO.getId());
		answer.setDescription_answer(answerDTO.getDescription_answer());
		answer.setDatetime(answerDTO.getDatetime());
		answer.setImg_src(answerDTO.getImg_src());
		answer.setQuestion(answerDTO.getQuestion());
		answer.setApproved_by(answerDTO.getApproved_by());
		answer.setCreated_by(answerDTO.getCreated_by());
		answer.setStatus(answerDTO.isStatus());
		return answer;
	}

}
