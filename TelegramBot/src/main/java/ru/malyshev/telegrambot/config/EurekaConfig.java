package ru.malyshev.telegrambot.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "eureka.client.service-url")
@Getter
@Setter
@ToString
public class EurekaConfig {

    @NotNull
    private String defaultZone;
}
