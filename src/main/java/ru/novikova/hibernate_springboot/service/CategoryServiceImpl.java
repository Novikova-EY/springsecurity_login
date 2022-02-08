package ru.novikova.hibernate_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikova.hibernate_springboot.entities.Category;
import ru.novikova.hibernate_springboot.repository.CategoryRepository;
import ru.novikova.hibernate_springboot.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryServiceImpl::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryServiceImpl::convertToDto);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName());
        Category saved = categoryRepository.save(category);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    private static CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
