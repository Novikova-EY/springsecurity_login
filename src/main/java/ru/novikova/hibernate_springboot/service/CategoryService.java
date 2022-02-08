package ru.novikova.hibernate_springboot.service;

import ru.novikova.hibernate_springboot.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();

    Optional<CategoryDto> findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    void deleteById(Long id);
}
