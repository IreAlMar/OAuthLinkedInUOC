package com.linkedIn.api.configuration;

import com.linkedIn.api.service.AccessToken;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfiguration {
    @Bean
    public AccessToken getAccessToken(){
        return new AccessToken(null, null);
    }

    @Bean
    public RestTemplate getRestTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
