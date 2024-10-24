package test_study.cafekiosk.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test_study.cafekiosk.client.mail.MailSendClient;
import test_study.cafekiosk.domain.history.MailSendHistory;
import test_study.cafekiosk.repository.MailSendHistoryRepository;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;


    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {

        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);
        if (true) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEmail(fromEmail)
                    .toEmail(toEmail)
                    .subject(subject)
                    .content(content)
                    .build());
            return true;
        }

        return true;
    }
}
