package cogent.com.controller;

import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;
import cogent.com.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/addquestion")
    public Question addQuestion(@RequestBody Question question) {
        return questionRepository.addQuestion(question);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable(value = "id") int id) {
        return questionRepository.findById(id).orElse(null);
    }

    @GetMapping("/allquestions")
    public List<Question> getAllUsers() {
        return questionRepository.findAll();
    }

}
