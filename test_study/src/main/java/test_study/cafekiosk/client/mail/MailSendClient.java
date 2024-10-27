package test_study.cafekiosk.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendClient {


    public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {
        //메일 전송
        log.info("메일 전송");
        return true;
    }

    public void a() {
        log.info("A");
    }

    public void b() {

        log.info("B");
    }

    public void c() {
        log.info("C");

    }
}
