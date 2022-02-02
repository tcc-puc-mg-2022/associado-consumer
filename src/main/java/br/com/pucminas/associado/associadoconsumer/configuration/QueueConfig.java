package br.com.pucminas.associado.associadoconsumer.configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class QueueConfig {

    public Connection getConnection() throws IOException, TimeoutException {
        final var factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("admin");

        return factory.newConnection();
    }

}
