package me.pro0xy.bankemulation.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import me.pro0xy.bankemulation.db.entity.Card;
import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.db.repo.CardRepository;
import me.pro0xy.bankemulation.db.repo.UserRepository;
import me.pro0xy.bankemulation.error.user.UserAlreadyExist;
import me.pro0xy.bankemulation.request.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:39</p>
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", email)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                String.valueOf(user.getPin()),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

    @SneakyThrows
    public User loadUserByUserName(@NonNull String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", email)
        ));
    }

    @SneakyThrows
    public User register(@NonNull RegisterRequest request){
        // Check if exist
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExist();

        // Creating new user
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPin(request.getPin());
        user.setRegisteredAt(LocalDateTime.now());
        user.setRoles(List.of(
                roleService.getUserRole()
        ));
        user.setCards(null);

        return userRepository.save(user);
    }

    public List<Card> getActiveCards(@NonNull User user){
        return cardRepository.findAllByUser(user);
    }
}
