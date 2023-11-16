package me.pro0xy.bankemulation.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.error.user.InvalidCredentials;
import me.pro0xy.bankemulation.request.auth.AuthRequest;
import me.pro0xy.bankemulation.request.auth.RegisterRequest;
import me.pro0xy.bankemulation.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:29</p>
 */

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @SneakyThrows
    public String createAuthToken(@NonNull AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), String.valueOf(authRequest.getPin()))
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentials();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        return jwtTokenUtils.generateToken(userDetails);
    }

    @SneakyThrows
    public User registerNewUser(@NonNull RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }
}
