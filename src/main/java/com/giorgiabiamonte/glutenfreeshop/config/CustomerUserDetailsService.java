package com.giorgiabiamonte.glutenfreeshop.config;

import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByUsername(username);
    }
}