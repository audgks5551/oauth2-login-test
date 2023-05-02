package io.mhan.oauth2clienttest.security;

import io.mhan.oauth2clienttest.security.CustomOauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOauth2Service customOauth2Service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ClientRegistration facebookClientRegistration = CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .clientId("1288617265388968")
                .clientSecret("fc81598cfd054986393aeed2608b282f")
                .redirectUri("{baseUrl}/{action}/custom/oauth2/code/{registrationId}")
                .build();

        InMemoryClientRegistrationRepository clientRegistrations =
                new InMemoryClientRegistrationRepository(facebookClientRegistration);

        http
                .oauth2Login(o -> o
                        .loginPage("/login")
                        .clientRegistrationRepository(clientRegistrations)

                        // filter1
                        // /oauth2/login
                        .authorizationEndpoint(a -> a
                                // /oauth/authorization/facebook
                                // /oauth2/login/facebook
                                .baseUri("/oauth2/login")
                        )

                        // filter2
                        // /login/custom/oauth2/code/*
                        .redirectionEndpoint(r -> r
                                .baseUri("/login/custom/oauth2/code/*")
                        )
                        .tokenEndpoint().and()
                        .userInfoEndpoint(u -> u
                                .userService(customOauth2Service)
                        )
                );

        return http.build();
    }
}
