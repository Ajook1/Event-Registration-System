package com.event.auth.service;

import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.User;
import com.event.auth.entity.UserDetails;
import com.event.auth.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public void saveUserDetails(User user, RegisterRequestDTO request) {

        UserDetails details = new UserDetails();

        details.setUser(user);
        details.setAddress(request.getAddress());
        details.setMobile(request.getMobile());
        details.setCity(request.getCity());
        details.setState(request.getState());
        details.setPincode(request.getPincode());

        userDetailsRepository.save(details);
    }
}