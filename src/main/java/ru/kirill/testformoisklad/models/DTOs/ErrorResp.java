package ru.kirill.testformoisklad.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResp {
    private String message;
}
