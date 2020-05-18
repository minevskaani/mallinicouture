package io.mallinicouture.backend.validation;

import io.mallinicouture.backend.domain.Client;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        if (client.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 charactesrs");
        }

        if (!client.getPassword().equals(client.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");
        }
    }
}
