package com.example.employee_api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String accessToken;
    private String type;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, String type, String username) {
        this.accessToken = accessToken;
        this.type = type;
        this.username = username;
    }

}