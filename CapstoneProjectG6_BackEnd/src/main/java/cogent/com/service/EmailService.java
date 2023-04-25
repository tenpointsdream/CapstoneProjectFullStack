package cogent.com.service;

import cogent.com.entity.Email;

public interface EmailService {

	String sendEmail(Email details);
	String sendMailWithAttachment(Email details);
}
