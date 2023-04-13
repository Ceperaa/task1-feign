package ru.clevertec.task1feign.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Logger.Level feignLoggerFactory() {
        return Logger.Level.FULL;
    }
}
