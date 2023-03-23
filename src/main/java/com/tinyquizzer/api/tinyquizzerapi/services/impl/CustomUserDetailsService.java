package com.tinyquizzer.api.tinyquizzerapi.services.impl;

import com.tinyquizzer.api.tinyquizzerapi.models.User;
import com.tinyquizzer.api.tinyquizzerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AtomicReference<org.springframework.security.core.userdetails.User> returnUserVar = new AtomicReference<>();
        Optional<User> user = userService.findByUsername(username);
        System.out.println(user.get());
        user.ifPresentOrElse(
                (value) -> {
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    value.getRoles().forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                        System.out.println("Added " + role.getName());
                    });
                    returnUserVar.set(new org.springframework.security.core.userdetails.User(value.getUsername(), value.getPassword(), authorities));
                }, () -> {
                    throw new UsernameNotFoundException("User not found");
                });
        System.out.println(returnUserVar.get());
        return returnUserVar.get();
    }
}
