package com.yadu.securedapp.secured.spring.boot.app.service;

import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;
import com.yadu.securedapp.secured.spring.boot.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(UserCredential userCredential) {
        userRepository.save(userCredential);
    }

    @Override
    public UserCredential getUser(String username) {
        UserCredential user= userRepository.findAll().stream()
                .filter((UserCredential uc)->uc.getUserName().equals(username)).findFirst().orElseThrow();
        System.out.println(user);
    return user;
    }
}
