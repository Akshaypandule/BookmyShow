package com.BookMyShow.ServiceImpl;

import com.BookMyShow.dto.UserDto;
import com.BookMyShow.entity.User;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User login(String email, String password) {

        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));

//        if(user!=null && user.getPassword.equals(password)
//        {
//            return user;
//        }
       return user;
    }
}
