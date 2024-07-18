package com.springboot.moov.service;

import com.springboot.moov.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    UserDto getUserByEmail(String email);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);
}
