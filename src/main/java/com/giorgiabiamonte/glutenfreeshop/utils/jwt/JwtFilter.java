package com.giorgiabiamonte.glutenfreeshop.utils.jwt;

import com.giorgiabiamonte.glutenfreeshop.config.CustomerUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomerUserDetailsService customerUserDetailsService; // TODO

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String header = request.getHeader("Authorization");
        System.out.println("header dalla richiesta: " + header);

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            token = header.substring(7);
            System.out.println("token richiesta senza bearer: " + token);
        }

        if (token != null && jwtUtils.validateToken(token)) {
            String username = jwtUtils.extractUsernameFromToken(token);
            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                log.info("Authorities {}", userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                        (userDetails.getUsername(), null, userDetails.getAuthorities());

//              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                log.info("Authenticated user with username {}", username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}