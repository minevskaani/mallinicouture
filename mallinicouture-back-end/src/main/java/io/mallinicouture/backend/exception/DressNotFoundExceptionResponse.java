package io.mallinicouture.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DressNotFoundExceptionResponse {

    private String dressNotFound;
}
