package io.mhan.oauth2clienttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Oauth2ClientTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientTestApplication.class, args);
    }

}
