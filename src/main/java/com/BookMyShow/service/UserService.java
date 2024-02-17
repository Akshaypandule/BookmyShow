package com.BookMyShow.service;

import com.BookMyShow.dto.UserDto;

public interface UserService {

    public UserDto addUser(UserDto userDto);

    public UserDto getUserById(Integer userId);

    UserDto getUserByEmail(String email);
}
