package cogent.com.service;

import cogent.com.entity.EmailDetails;

public interface EmailService {

	String sendEmail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}
