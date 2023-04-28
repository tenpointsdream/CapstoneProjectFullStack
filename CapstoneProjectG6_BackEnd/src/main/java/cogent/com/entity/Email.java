package cogent.com.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String recipient;
    private String subject;
    private String msgBody;
}
