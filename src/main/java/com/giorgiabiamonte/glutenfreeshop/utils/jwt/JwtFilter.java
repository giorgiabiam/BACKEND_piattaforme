package com.giorgiabiamonte.glutenfreeshop.utils.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    // private final CustomerUserDetailsService customerUserDetailsService; // TODO

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {




        //String text = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("djhdfkjdgf");
        String token= request.getHeader("token");
        //boolean tk_valid=jwtUtils.validateToken(token);

        //String token = jwtUtils.generateToken("request");

        log.debug("Token in JWT-filter {}", token);

//        if (token != null && jwtUtils.validateToken(token)) {
//            String username = jwtUtils.extractUsername(token);
//            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
//            if (userDetails != null) {
//                log.debug("Authorities {}", userDetails.getAuthorities());
//
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
//
//                log.debug("Authenticated user with username {}", username);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
        filterChain.doFilter(request, response);
    }

}