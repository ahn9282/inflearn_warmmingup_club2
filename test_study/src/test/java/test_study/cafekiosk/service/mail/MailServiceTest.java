package test_study.cafekiosk.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import test_study.cafekiosk.client.mail.MailSendClient;
import test_study.cafekiosk.domain.history.MailSendHistory;
import test_study.cafekiosk.repository.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {


    @Mock
    MailSendClient mailSendClient;

    @Mock
    MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        //Given
        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        //Mockito.when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);
        //아래에 Mockito.doReturn이 있어 Stubbing이되었기에 ㅇ이를 주석을 풀 경우 unnecessrayStubbing 예외가 발생하고 테스트는 실패한다.

        Mockito.doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());

        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);


        //When
        boolean result = mailService.sendMail("", "", "", "");

        //Then
        assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));


    }

}