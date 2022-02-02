package br.com.pucminas.associado.associadoconsumer.consumer;

import br.com.pucminas.associado.associadoconsumer.service.CarteirinhaService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RabbitMqConsulmer {

    @NonNull
    private CarteirinhaService service;

    @RabbitListener(queues = "${rabbitmq.queue.consume}")
    public void receiveMessage(String message) {
        this.service.processarCarteirinha(message);
    }

}
