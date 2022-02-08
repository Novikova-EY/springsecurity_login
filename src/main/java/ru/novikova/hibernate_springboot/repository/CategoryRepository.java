package ru.novikova.hibernate_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikova.hibernate_springboot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
