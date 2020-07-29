package com.yadu.securedapp.secured.spring.boot.app.service;

import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;

public interface UserService {
    void save(UserCredential userCredentials);
    UserCredential getUser(String username);
}
