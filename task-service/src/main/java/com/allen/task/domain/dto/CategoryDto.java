package com.allen.task.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "Nombre de categoria no puede ser null ni vacío")
    private String name;
    @NotNull(message = "El userID de la tarea no puede ser nulo o vacío")
    private Long userId;
}
