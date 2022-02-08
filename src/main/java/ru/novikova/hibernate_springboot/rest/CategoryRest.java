package ru.novikova.hibernate_springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.novikova.hibernate_springboot.exceptions.CommonError;
import ru.novikova.hibernate_springboot.exceptions.NotFoundException;
import ru.novikova.hibernate_springboot.service.CategoryService;
import ru.novikova.hibernate_springboot.service.dto.CategoryDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRest {

    private CategoryService categoryService;

    @Autowired
    public CategoryRest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> listPage() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto listPage(@PathVariable("id") Long id) {
        return categoryService.findById(id)
                .orElseThrow(() -> new NotFoundException("Category doesn't exist"));
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            throw new IllegalArgumentException("This category exists");
        }
        return categoryService.save(categoryDto);
    }

    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new IllegalArgumentException("This category doesn't exist");
        }
        return categoryService.save(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonError notFoundExceptionHandler(NotFoundException ex) {
        return new CommonError(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonError badRequestExceptionHandler(IllegalArgumentException ex) {
        return new CommonError(ex.getMessage());
    }
}
