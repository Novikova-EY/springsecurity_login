package ru.novikova.hibernate_springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.novikova.hibernate_springboot.exceptions.NotFoundException;
import ru.novikova.hibernate_springboot.exceptions.CommonError;
import ru.novikova.hibernate_springboot.service.ProductService;
import ru.novikova.hibernate_springboot.service.dto.ProductDto;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductRest {
    private final ProductService productService;

    @Autowired
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> listPage(@RequestParam("nameFilter") Optional<String> nameFilter,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size,
                                     @RequestParam("sort") Optional<String> sort) {

        return productService.findAll(
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id")
        );
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new IllegalArgumentException("This product exists");
        }
        return productService.save(productDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new IllegalArgumentException("This product doesn't exist");
        }
        return productService.save(productDto);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
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
