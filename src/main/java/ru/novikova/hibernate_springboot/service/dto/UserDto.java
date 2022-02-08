package ru.novikova.hibernate_springboot.service.dto;

import ru.novikova.hibernate_springboot.entities.Role;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserDto {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
