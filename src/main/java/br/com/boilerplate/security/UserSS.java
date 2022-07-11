package br.com.boilerplate.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String password;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String email, String password, String name) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authorities = Collections.emptyList();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
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

    public boolean hasRole(String role) {
        return getAuthorities().contains(new SimpleGrantedAuthority(role));
    }

}
