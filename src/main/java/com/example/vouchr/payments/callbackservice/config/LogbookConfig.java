package com.example.vouchr.payments.callbackservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogFormatter;
import org.zalando.logbook.HttpLogFormatter;

@Configuration
public class LogbookConfig {

    @Bean
    public HttpLogFormatter httpLogFormatter() {
        return new DefaultHttpLogFormatter();
    }
}
