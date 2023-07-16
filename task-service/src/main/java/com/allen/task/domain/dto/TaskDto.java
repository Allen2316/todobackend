package com.allen.task.domain.dto;

import com.allen.task.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotEmpty(message = "El nombre de la tarea no puede ser nulo o vacío")
    private String name;

    private String description;
    private boolean completed;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "No puede ser Nullo o vacio")
    private Category category;


}
