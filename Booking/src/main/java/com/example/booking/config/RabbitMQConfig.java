package com.example.booking.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jojo
 * @since 1.0.0
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue() {
        return new Queue("queue", true);
    }
}