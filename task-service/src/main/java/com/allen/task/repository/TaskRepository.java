package com.allen.task.repository;

import com.allen.task.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allen.task.domain.Task;

import java.util.List;

@Repository //esto es opcional porque ya hereda de JPA
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategory(Category category);
    Task findByName(String name);
}
