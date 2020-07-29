package com.yadu.securedapp.secured.spring.boot.app.models;


import lombok.ToString;



public class UserCredentialResponse {
    private final String jwt;

    public UserCredentialResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "UserCredentialResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
