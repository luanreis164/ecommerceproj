package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.dto.EmailDTO;
import com.lntech.ecommerce.security.JWTUtil;
import com.lntech.ecommerce.security.UserSS;
import com.lntech.ecommerce.services.AuthService;
import com.lntech.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthContoller {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers","Authorization");
        return ResponseEntity.noContent().build();

    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO){
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();

    }




}
