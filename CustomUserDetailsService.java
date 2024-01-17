package com.example.itjobportal.services;

import com.example.itjobportal.models.User;
import com.example.itjobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Użytkownik o adresie email " + email + " nie istnieje.");
        }

        User user = userOptional.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

}
