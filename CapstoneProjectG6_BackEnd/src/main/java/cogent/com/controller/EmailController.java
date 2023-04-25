package cogent.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.EmailDetails;
import cogent.com.service.EmailService;

@RestController("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmail")
	public String sendMail(@RequestBody EmailDetails details) {
		String status = emailService.sendEmail(details);

		return status;
	}

	@PostMapping("/sendEmailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details) {
		String status = emailService.sendMailWithAttachment(details);

		return status;
	}
}
