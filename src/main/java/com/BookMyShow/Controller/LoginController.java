//package com.BookMyShow.Controller;
//
//import com.BookMyShow.ServiceImpl.LoginServiceImpl;
//import com.BookMyShow.ServiceImpl.UserServiceImpl;
//import com.BookMyShow.entity.Login;
//import com.BookMyShow.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("user/v1/login")
//public class LoginController
//{
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Autowired
//    private LoginServiceImpl loginService;
//
//    @PostMapping
//    public ResponseEntity<User> login(@RequestBody Login login){
//        User user = loginService.login(login.getEmail(), login.getPassword());
//
//        if(user!=null){
//            return ResponseEntity.ok(user);
//        }
//        else {
//            return ResponseEntity.status(401).body(user);
//        }
//    }
//
//}
