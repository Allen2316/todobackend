package com.allen.task.service;

import com.allen.task.domain.dto.CategoryDto;

import java.util.List;

/**
 * Gestionar CRUD de categorias
 *
 * @author Allen
 * @version 14/07/2023
 */
public interface CategoryService {
    /**
     * Obtener categoria por ID
     *
     * @param id ID de la categoria
     * @return La categoria encontrada
     */
    CategoryDto getById(long id);

    /**
     * Obtener todas las categorias
     *
     * @return Lista de categorias
     */
    List<CategoryDto> getAll();

    /**
     * Crear nueva categoria
     *
     * @param categoryDto Objeto que contiene la información de categoría
     * @return La categoria creada
     */
    CategoryDto create(CategoryDto categoryDto);

    /**
     * Actualizar categoria por ID
     *
     * @param id          ID de la categoria
     * @param categoryDto Objeto que contiene la información de categoría
     * @return La categoria actualizada
     */
    CategoryDto updateById(long id, CategoryDto categoryDto);

    /**
     * Eliminar categoria por ID
     *
     * @param id ID de la categoria
     * @return ID de la categoria eliminada
     */
    Long deleteById(long id);

    /**
     * Obtener las categorias por usuario
     * @param userId ID del usuario para otbener las tareas
     * @return La lista de tareas pertenecientes a un usuario
     */
    List<CategoryDto> findByUserId(long userId);

    /**
     * Obtener las categorias por nombre
     * @param name Nombre de la categoria
     * @return La categoría con ese nombre
     */
    CategoryDto findByName(String name);

}
