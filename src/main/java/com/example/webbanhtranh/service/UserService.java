package com.example.webbanhtranh.service;

import com.example.webbanhtranh.dto.UserDto;
import com.example.webbanhtranh.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}