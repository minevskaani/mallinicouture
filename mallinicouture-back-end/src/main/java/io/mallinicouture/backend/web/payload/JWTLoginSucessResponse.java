package io.mallinicouture.backend.web.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTLoginSucessResponse {

    private boolean sucess;
    private String token;

}
