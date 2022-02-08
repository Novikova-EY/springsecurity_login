package ru.novikova.hibernate_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikova.hibernate_springboot.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
