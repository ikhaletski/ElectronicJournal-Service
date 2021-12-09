package com.iba.electronicjournalservice.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iba.electronicjournalservice.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final Long classId;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String firstName, String lastName, String email, String phone, Long classId, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.classId = classId;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }

    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String getPhone() {
        return phone;
    }

    @JsonIgnore
    public Long getClassId() {
        return classId;
    }
}
