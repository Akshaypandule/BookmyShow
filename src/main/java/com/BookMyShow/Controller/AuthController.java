package com.BookMyShow.Controller;

import com.BookMyShow.Security.JwtHelper;
import com.BookMyShow.ServiceImpl.UserServiceImpl;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.payload.CustomUserDetails;
import com.BookMyShow.payload.JwtAuthRequest;
import com.BookMyShow.payload.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class AuthController {

    @Autowired
    private JwtHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetails customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        authenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        String generatedToken = jwtTokenHelper.generateToken(userDetails);

        JwtResponse jwtAuthResponse = new JwtResponse();
        jwtAuthResponse.setToken(generatedToken);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);


    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){
            throw new ResourceNotFound("Invalid username and password");
        }

    }

}

