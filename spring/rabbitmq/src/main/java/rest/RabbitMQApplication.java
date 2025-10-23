package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitMQApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQApplication.class);

    static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class, args);
    }
}
