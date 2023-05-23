package com.giorgiabiamonte.glutenfreeshop.models.entities;


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

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Getter
@Table(name="Utente")
public class Utente implements UserDetails, Serializable {

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    List<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Integer ID;

    @Column(name="username", nullable=false , unique = true)
    //@Size(min = 2, max = 15)
    //@NotBlank //equivale a not null e lunghezza dopo trim >0
    //@Pattern(regexp = "") //TODO non si accettano spazi bianchi
    private String username;

    @Column(name="password", nullable=false)
    //@Size(min = 4, max = 15)
    //@NotBlank
    //@Pattern(regexp = "") //TODO non si accettano spazi bianchi
    private String password;

//    @Column(name="convenzionato", nullable=false)
//    private boolean convenzionato;

//    @Column(name="saldo")
//    private double saldo;

    @Column(name="indirizzo", nullable = false)
    //@NotBlank
    private String indirizzo;

    @Column(name="nome", nullable=false)
    //@Size(min = 2, max = 15)
    //@NotBlank
    private String nome;

    @Column(name="cognome", nullable=false)
    //@Size(min = 2, max = 15)
    //@NotBlank
    private String cognome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "acquirente")
    private List<Acquisto> listaAcquisti = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ProdottoInMagazzino> preferiti = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
//        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
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
