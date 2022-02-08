package ru.novikova.hibernate_springboot.service;

import ru.novikova.hibernate_springboot.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(UserDto userDto);

    void deleteById(Long id);

}
