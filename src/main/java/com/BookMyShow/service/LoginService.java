package com.BookMyShow.service;


import com.BookMyShow.dto.UserDto;
import com.BookMyShow.entity.User;

public interface LoginService {

    public User login(String email, String password);
}
