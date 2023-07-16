package com.allen.task.service;

import com.allen.task.domain.Category;
import com.allen.task.domain.Task;

import com.allen.task.domain.dto.TaskDto;
import com.allen.task.repository.TaskRepository;
import com.allen.task.service.impl.TaskServiceImpl;
import com.allen.task.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class TaskServiceMockTest {
    private TaskRepository taskRepositoryMock;
    private TaskService taskService;

    //mock sirve para pruebas unitarias sin levantar el contexto de springboot ni accediendo a la bd, si no solo mocks

    @BeforeEach
    public void setup() {
        //Se mockea el repository
        this.taskRepositoryMock = Mockito.mock(TaskRepository.class);
        this.taskService = new TaskServiceImpl(this.taskRepositoryMock);

        Task taskEntity = Task.builder()
                .id(1L)
                .category(Category.builder().id(1L).build())
                .completed(false)
                .description("Es la descripcion")
                .name("Task1")
                .build();

        Mockito.when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(taskEntity));

    }

    @Test
    public void whenValidGetId_ThenReturnTask() {
        TaskDto found = taskService.getById(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Task1");
    }

    @Test
    public void whenValidUpdateTask_ThenReturnNewTask() {

        Task task = Task.builder()
                .id(1L)
                .category(Category.builder().id(1L).build())
                .completed(false)
                .description("Es la descripcion editada")
                .name("Task1 Updated")
                .build();
        TaskDto taskDto = Mapper.modelMapper().map(task, TaskDto.class);

        Mockito.when(taskRepositoryMock.save(task)).thenAnswer(ele -> ele.getArgument(0));
        log.info("TaskDto " + taskDto);

        TaskDto newTask = taskService.updateById(taskDto.getId(), taskDto);

        Assertions.assertThat(newTask.getId()).isEqualTo(1L); // El ID no cambia
        Assertions.assertThat(newTask.getName()).isEqualTo("Task1 Updated");
        Assertions.assertThat(newTask.getDescription()).isEqualTo("Es la descripcion editada");
        Assertions.assertThat(newTask.isCompleted()).isFalse();
    }
}
