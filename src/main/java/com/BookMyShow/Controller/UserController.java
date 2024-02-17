package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.UserServiceImpl;
import com.BookMyShow.dto.UserDto;
import com.BookMyShow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto save = userService.addUser(userDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }
}
