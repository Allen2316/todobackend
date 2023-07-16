package com.allen.task.controller;

import com.allen.task.controller.exceptions.ErrorMessage;
import com.allen.task.domain.dto.CategoryDto;
import com.allen.task.exceptions.CustomNotFoundException;
import com.allen.task.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.categoryService.getById(id));
        }catch (CustomNotFoundException ex){
            log.info("Error ==== ", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(@RequestParam(value = "id", required = false) Long id) {
        List<CategoryDto> categories;
        if(id == null){
            categories = this.categoryService.getAll();
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else{
            categories = this.categoryService.findByUserId(id);
            if (categories.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto categoryDto, BindingResult result) {

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
        }
        return new ResponseEntity<>(this.categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json;charset=UTF-8", value = "/{id}")
    public ResponseEntity<CategoryDto> updateById(@PathVariable long id, @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
        }
        return new ResponseEntity<>(this.categoryService.updateById(id, categoryDto), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(long id) {
        try {
            return ResponseEntity.ok(this.categoryService.deleteById(id));
        }catch (CustomNotFoundException ex){
            log.info("Error ==== ", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/name")
    public ResponseEntity<CategoryDto> findByName(@RequestParam(name = "name") String name) {
        try {
            return ResponseEntity.ok(this.categoryService.findByName(name));
        }catch (Exception ex){
            log.info("Error ==== ", ex);
            return ResponseEntity.notFound().build();
        }
    }
}
