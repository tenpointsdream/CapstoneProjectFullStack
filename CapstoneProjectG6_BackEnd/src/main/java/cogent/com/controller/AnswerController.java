package cogent.com.controller;

import cogent.com.entity.Answer;
import cogent.com.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/addAnswer")
    public void addAnswer(@Validated @RequestBody Answer answer) {
        answerService.addAnswer(answer);
    }

    @PostMapping("/updateAnswer")
    public void updateAnswer(@Validated @RequestBody Answer answer) {
        answerService.updateAnswer(answer);
    }

    @DeleteMapping("/deleteAnswerById")
    public void deleteAnswerById(@Validated @RequestBody int id) {
        answerService.deleteAnswerById(id);
    }

    @GetMapping("/getAllAnswers")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/getAllAnswerFalse")
    public List<Answer> getAllAnswersFalse() {
        return answerService.getAllAnswersFalse();
    }

    @GetMapping("/getAllAnswerByQuestionId")
    public List<Answer> getAllAnswersByQuestionId(@Validated @RequestBody int questionId) {
        return answerService.getAllAnswersByQuestionId(questionId);
    }

    @GetMapping("/getAllAnswerById")
    public List<Answer> getAllAnswersById(@Validated @RequestBody int answerId) {
        return answerService.getAllAnswersById(answerId);
    }
}
