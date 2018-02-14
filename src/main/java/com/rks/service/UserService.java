package com.rks.service;

import com.rks.dto.RequestUserDto;
import com.rks.dto.ResponseUserDto;
import com.rks.model.Cart;
import com.rks.model.UserDetails;
import com.rks.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    private final static int INITIAL_TOTAL = 0;

    public ResponseUserDto createUser(RequestUserDto requestUserDto){

        UserDetails userDetails = userDetailsRepository.save(new UserDetails(requestUserDto.getUserFirstName(),
                requestUserDto.getUserLastName(),
                requestUserDto.getAddress(),
                requestUserDto.getContact(),
                requestUserDto.getEmail(),
                new Cart(new Double(INITIAL_TOTAL))));

        return new ResponseUserDto(userDetails.getUserId(),
                                   userDetails.getUserFirstName(),
                                   userDetails.getUserLastName(),
                                   userDetails.getAddress(),
                                   userDetails.getContact(),
                                   userDetails.getEmail());
    }

    public List<ResponseUserDto> findAll() {
         List<UserDetails> userDetails = userDetailsRepository.findAll();

        return userDetails.stream()
                .map(user -> new ResponseUserDto(user.getUserId(),
                                                 user.getUserFirstName(),
                                                 user.getUserLastName(),
                                                 user.getAddress(),
                                                 user.getContact(),
                                                 user.getEmail()))
                .collect(Collectors.toList());
    }
}
