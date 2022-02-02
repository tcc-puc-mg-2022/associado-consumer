package br.com.pucminas.associado.associadoconsumer.service;

import br.com.pucminas.associado.associadoconsumer.configuration.QueueConfig;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CarteirinhaService {

    @NonNull
    private QueueConfig queueConfig;


    private static final String NM_CART_QUEUE_OUT = "associado.cart.out";

    public void processarCarteirinha(String messageCart) {
        final var id = messageCart.split("_id_")[1];
        try {
            final var connection = this.queueConfig.getConnection();
            try (var channel = connection.createChannel()) {
                final var message = "creation_processed_id_" + id;
                channel.basicPublish("", NM_CART_QUEUE_OUT, null, message.getBytes(StandardCharsets.UTF_8));
            }
            connection.close();
            log.info("Carteirinha processada!");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
