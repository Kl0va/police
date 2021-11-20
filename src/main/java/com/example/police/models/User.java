package com.example.police.models;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @NotEmpty(message = "Поле должно быть заполнено")
    @NotNull
    private String username;
    @NotEmpty(message = "Поле должно быть заполнено")
    @NotNull
    private String password;
    @NotEmpty(message = "Поле должно быть заполнено")
    @Size(min = 2,message = "От 2 символов")
    @NotNull
    private String first_name,second_name;
    @NotEmpty(message = "Поле должно быть заполнено")
    @Size(min = 4, max = 4)
    @NotNull
    private String num_pass;
    @NotEmpty(message = "Поле должно быть заполнено")
    @Size(min = 6, max = 6)
    @NotNull
    private String ser_pass;
    @Nullable
    private String middle_name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Role role;
    @Nullable
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Position position;
    @Nullable
    @ManyToMany
    @JoinTable(name = "user_statement",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "statement"))
    private List<Statement> statements;

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getNum_pass() {
        return num_pass;
    }

    public void setNum_pass(String num_pass) {
        this.num_pass = num_pass;
    }

    public String getSer_pass() {
        return ser_pass;
    }

    public void setSer_pass(String ser_pass) {
        this.ser_pass = ser_pass;
    }

    @Nullable
    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(@Nullable String middle_name) {
        this.middle_name = middle_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Nullable
    public Position getPosition() {
        return position;
    }

    public void setPosition(@Nullable Position position) {
        this.position = position;
    }

    @Nullable
    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(@Nullable List<Statement> statements) {
        this.statements = statements;
    }
}
