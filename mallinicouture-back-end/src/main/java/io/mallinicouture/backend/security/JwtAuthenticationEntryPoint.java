package io.mallinicouture.backend.security;

import com.google.gson.Gson;
import io.mallinicouture.backend.exception.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        String jsonResponse = new Gson().toJson(loginResponse);

        response.setContentType("application/json");
        // response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(401);
        response.getWriter().print(jsonResponse);
    }

}
