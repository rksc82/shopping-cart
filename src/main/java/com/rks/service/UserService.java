package com.rks.service;

import com.rks.dto.UserDto;
import com.rks.model.Cart;
import com.rks.model.UserDetails;
import com.rks.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDto createUser(UserDto userDto){

        UserDetails userDetails = userDetailsRepository.save(new UserDetails(userDto.getUserFirstName(),
                userDto.getUserLastName(),
                userDto.getUserFirstName(),
                userDto.getContact(),
                userDto.getEmail(),
                new Cart(new Double(0))));

        userDto.setUserId(userDetails.getUserId());
        return userDto;
    }

    public List<UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }
}
