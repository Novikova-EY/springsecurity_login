package ru.novikova.hibernate_springboot.service;

import org.springframework.data.domain.Page;
import ru.novikova.hibernate_springboot.service.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    Optional<ProductDto> findById(Long id);

    ProductDto save(ProductDto product);

    void deleteById(Long id);

}
