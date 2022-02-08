package ru.novikova.hibernate_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.novikova.hibernate_springboot.entities.*;
import ru.novikova.hibernate_springboot.repository.CategoryRepository;
import ru.novikova.hibernate_springboot.repository.ProductRepository;
import ru.novikova.hibernate_springboot.service.dto.ProductDto;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<String> nameFilter, Integer page,
                                    Integer size, String sort) {
        Specification<Product> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
        }
            return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                    .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElse(null);
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(), category);
        Product saved = productRepository.save(product);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto convertToDto(Product prod) {
        return new ProductDto(
                prod.getId(),
                prod.getName(),
                prod.getDescription(),
                prod.getPrice(),
                prod.getCategory() != null ? prod.getCategory().getId() : null,
                prod.getCategory() != null ? prod.getCategory().getName() : null
        );
    }
}
