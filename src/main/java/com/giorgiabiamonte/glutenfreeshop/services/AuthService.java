package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.LoginRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import com.giorgiabiamonte.glutenfreeshop.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    @Autowired
    private UtenteRepository urepo;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public String authenticate(LoginRequest req){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Utente utente = (Utente) authentication.getPrincipal();
        return jwtUtils.generateToken(utente.getUsername());
    }
}
