package io.mallinicouture.backend.exception;

import lombok.Data;

@Data
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid username";
        this.password = "Invalid password";
    }
}
