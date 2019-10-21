package cn.rumoss.study.springbootactivemq.config;

import cn.rumoss.study.springbootactivemq.entity.Mail;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public Destination mailQueue() {
        return new ActiveMQQueue("mail.message.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {

        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("string", String.class);
        typeIdMappings.put("mail", Mail.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;

    }

}
