package com.example.vouchr.payments.callbackservice;

import ca.vouchr.payments.callback.config.VouchrConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"ca.vouchr.payments","com.example.vouchr.payments.callbackservice"})
@EnableConfigurationProperties({VouchrConfigurationProperties.class})
public class CallbackServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CallbackServiceApplication.class, args);
    }

}
