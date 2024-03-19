package com.example.apirestevents.serviceImpl;

import com.example.apirestevents.model.dtos.ExternalUserDTO;
import com.example.apirestevents.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
@Service
public class ExternalUserServiceImpl implements ExternalUserService {
    private final WebClient webClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ExternalUserServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    // Método para obtener usuarios con WebClient
    public List<ExternalUserDTO> fetchUsersWithWebClient() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(ExternalUserDTO.class).collectList().block();
    }

    // Método para obtener usuarios con RestTemplate
    public List<ExternalUserDTO> fetchUsersWithRestTemplate() {
        ExternalUserDTO[] userArray = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", ExternalUserDTO[].class);
        return Arrays.asList(userArray);
    }
}
