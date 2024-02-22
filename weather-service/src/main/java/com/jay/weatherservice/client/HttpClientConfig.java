package com.jay.weatherservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {
    private final String baseUrl = "https://api.openweathermap.org";
    @Bean
    WeatherHttpClient weatherService() {
        RestClient restClient = RestClient.create(baseUrl);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(WeatherHttpClient.class);
    }
}
