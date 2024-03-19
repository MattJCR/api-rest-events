package com.example.apirestevents.service;

import com.example.apirestevents.model.dtos.ExternalUserDTO;

import java.util.List;

public interface ExternalUserService {
    public List<ExternalUserDTO> fetchUsersWithWebClient();

    // Método para obtener usuarios con RestTemplate
    public List<ExternalUserDTO> fetchUsersWithRestTemplate();
}
