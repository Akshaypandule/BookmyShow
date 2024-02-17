package com.BookMyShow.payload;

import com.BookMyShow.entity.User;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));
        return user;
    }
}
