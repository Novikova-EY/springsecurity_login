package ru.novikova.hibernate_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.novikova.hibernate_springboot.entities.User;
import ru.novikova.hibernate_springboot.repository.UserRepository;
import ru.novikova.hibernate_springboot.service.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserServiceImpl::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserServiceImpl::convertToDto);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getRoles());
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User saved = userRepository.save(user);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRoles()
        );
    }
}
