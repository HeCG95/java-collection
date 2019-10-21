package cn.rumoss.study.springbootactivemq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Data
@Component
public class MailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        return new SimpleMailMessage();
    }

}
