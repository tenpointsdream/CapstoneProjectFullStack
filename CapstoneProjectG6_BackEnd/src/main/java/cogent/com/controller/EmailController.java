package cogent.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.Email;
import cogent.com.service.EmailService;

@RestController("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmail")
	public String sendMail(@RequestBody Email details) {
		return emailService.sendEmail(details);
	}

	@PostMapping("/sendEmailWithAttachment")
	public String sendMailWithAttachment(@RequestBody Email details) {
		return emailService.sendMailWithAttachment(details);
	}
}
