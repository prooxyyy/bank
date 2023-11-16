package me.pro0xy.bankemulation.provider;

import me.pro0xy.bankemulation.encoder.PinEncoder;
import me.pro0xy.bankemulation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:53</p>
 */

@Component
public class PinAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder pinEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pin = (String) authentication.getCredentials();
        UserDetails userDetails = userService.loadUserByUsername(email);

        if (pinEncoder.matches(pin, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Invalid PIN");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

