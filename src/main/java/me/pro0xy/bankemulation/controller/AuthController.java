package me.pro0xy.bankemulation.controller;

import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.request.auth.AuthRequest;
import me.pro0xy.bankemulation.request.auth.RegisterRequest;
import me.pro0xy.bankemulation.response.ApiResponse;
import me.pro0xy.bankemulation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:34</p>
 */

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<ApiResponse<Object>> auth(@RequestBody AuthRequest authRequest){
        String token = authService.createAuthToken(authRequest);
        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .addKeyValue("token", token)
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@RequestBody RegisterRequest registerRequest){
        User user = authService.registerNewUser(registerRequest);
        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .addKeyValue("user", user)
                .build();
    }
}
