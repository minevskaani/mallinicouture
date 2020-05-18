package io.mallinicouture.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DressNotFoundException extends RuntimeException {
    public DressNotFoundException(String message) {
        super(message);
    }

    public DressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
