package com.example.itjobportal.services;

import com.example.itjobportal.models.Role;
import com.example.itjobportal.models.User;

import com.example.itjobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public void registerNewUser(User user) {
        // Dodaj logikę walidacji
        validateUserData(user);

        // Sprawdź, czy użytkownik o podanym adresie email już istnieje
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Użytkownik o podanym adresie email już istnieje");
        }

        // Zakoduj hasło przed zapisaniem do bazy danych
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Ustaw domyślną rolę dla nowego użytkownika (możesz dostosować do własnych potrzeb)
        Role userRole = new Role();
        userRole.setId(1L); // Identyfikator roli użytkownika, zakładam, że rola o id 1 to rola użytkownika
        user.setRole_id(userRole);

        // Zapisz użytkownika do bazy danych
        userRepository.save(user);
    }


    private void validateUserData(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Użytkownik o podanym adresie email już istnieje");
        }

        // Dodatkowe reguły walidacji, np. sprawdzanie wymagań dotyczących hasła
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Hasło musi mieć co najmniej 8 znaków");
        }

        // Dodaj inne reguły walidacji, jeśli są potrzebne
    }
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null; // Brak zalogowanego użytkownika lub nie jest instancją klasy User
    }
}
