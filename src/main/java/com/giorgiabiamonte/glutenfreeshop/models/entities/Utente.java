package com.giorgiabiamonte.glutenfreeshop.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Data @ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Getter
@Table(name="Utente")
public class Utente implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Integer ID;

    @Column(name="username", nullable=false , unique = true)
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="indirizzo", nullable = false)
    private String indirizzo;

    @Column(name="nome", nullable=false)
    private String nome;

    @Column(name="cognome", nullable=false)
    private String cognome;

    @Column(name="ruolo", nullable=false)
    private String ruolo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "acquirente")
    @JsonIgnore
    private List<Acquisto> listaAcquisti = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.ruolo ));
        System.out.println("ruolo  "+ this.ruolo);
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Utente utente = (Utente) o;
        return getID() != null && Objects.equals(getID(), utente.getID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
