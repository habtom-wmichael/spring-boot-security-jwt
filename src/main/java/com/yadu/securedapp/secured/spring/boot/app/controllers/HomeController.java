package com.yadu.securedapp.secured.spring.boot.app.controllers;

import com.yadu.securedapp.secured.spring.boot.app.config.MyUserdetailService;
import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;
import com.yadu.securedapp.secured.spring.boot.app.models.UserCredentialResponse;
import com.yadu.securedapp.secured.spring.boot.app.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/app/v1")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private MyUserdetailService userdetailService;

    @GetMapping(value = "/")
    public String home() {
        return "welcome to yadu-online-shop";
    }

    @GetMapping(value ="/shop")
    public String shopingPage() {
        return " you are authorized to Yadu's Store. add item to your cart";
    }

    @PostMapping("/login")
    public ResponseEntity<UserCredentialResponse> login(@RequestBody UserCredential userCredential) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredential.getUserName(), userCredential.getPassword())
            );

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("incorrect password or Username");
        }

        UserDetails userDetails =userdetailService.loadUserByUsername(userCredential.getUserName());
        System.out.println(userDetails);
        String jwt=jwtUtility.generateToken(userDetails);
        return new ResponseEntity<>(new UserCredentialResponse(jwt), HttpStatus.OK);
    }

}
