package com.allen.task.controller.exceptions;

import com.allen.task.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data @Builder
public class ErrorMessage {
    private String code;
    private List<Map<String, String>> message;

    /**
     * Formatear mensaje de error de validacion de campos
     * @param result El resultado de la validación
     * @return El mensaje del error en formato JSON pero en String
     */
    public static String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .message(errors)
                .build();
        String jsonString = "";
        try {
            jsonString = Mapper.objectMapper().writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
