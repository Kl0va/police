package com.example.police.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority{
    @Id
    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    Collection<User> users;

    public Role(String name){
        this.name = name;
    }
    public Role(){

    }

    public String getName() {
        return name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
