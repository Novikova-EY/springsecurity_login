package ru.novikova.hibernate_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.novikova.hibernate_springboot.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u " +
            "FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "WHERE u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);
}
