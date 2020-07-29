package com.yadu.securedapp.secured.spring.boot.app.config;

import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;
import com.yadu.securedapp.secured.spring.boot.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserdetailService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential=userService.getUser(username);
        System.out.println(userCredential.toString());
        return new User(userCredential.getUserName(),userCredential.getPassword(), new ArrayList<>());
    }
}
