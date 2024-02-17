package com.BookMyShow.ServiceImpl;

//import com.BookMyShow.Config.ModelMapper;
//import com.BookMyShow.Config.ModelMapperConfig;
import com.BookMyShow.dto.UserDto;
import com.BookMyShow.entity.User;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto addUser(UserDto userDto)
    {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = dtoToUser(userDto);
//        user.setId(UUID.randomUUID().toString());

        User save = userRepository.save(user);
        return userToDto(user);
    }

    @Override
    public UserDto getUserById(Integer userid)
    {
        Optional<User> optional = userRepository.findById(userid);

        if(optional.isPresent()){
            User user = optional.get();
            return userToDto(user);
        }
        else {
            throw new ResourceNotFound("Id is Not Found"+userid);
        }

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));
        return userToDto(user);
    }


    // UserDto User

    public User dtoToUser(UserDto userDto){

        User user = modelMapper.map(userDto, User.class);
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userdto = modelMapper.map(user, UserDto.class);

        return userdto;
    }
}
