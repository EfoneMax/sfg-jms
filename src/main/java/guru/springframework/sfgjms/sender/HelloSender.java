package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@AllArgsConstructor
public class HelloSender {
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("I'm sending a message");
        HelloWorldMessage message = HelloWorldMessage.builder()
                .uuid(UUID.randomUUID())
                .message("Hello world message")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("message sent!");
    }
}
