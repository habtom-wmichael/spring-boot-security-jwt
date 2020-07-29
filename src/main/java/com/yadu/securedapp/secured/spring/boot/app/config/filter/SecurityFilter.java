package com.yadu.securedapp.secured.spring.boot.app.config.filter;

import com.yadu.securedapp.secured.spring.boot.app.config.MyUserdetailService;
import com.yadu.securedapp.secured.spring.boot.app.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private MyUserdetailService userdetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
         String authenticationHeader=httpServletRequest.getHeader("Authorization");
        String userName=null;
        String jwt=null;
        try {
            if (authenticationHeader!=null && authenticationHeader.startsWith("Bearer ")) {
                jwt = authenticationHeader.substring(7);
               // System.out.println("jwt: " + jwt);
                userName = jwtUtility.extractUsername(jwt);

                if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails=userdetailService.loadUserByUsername(userName);
                    if(jwtUtility.validateToken(jwt,userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                         userDetails,null, userDetails.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }}
            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }catch (AuthorizationServiceException exception){
            exception.printStackTrace();
        }

    }
}
