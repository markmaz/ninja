package com.ninjarmm.services.impl;

import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserService userService;

    public UserDetailsServiceImpl(final IUserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.findByUserName(username);
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
