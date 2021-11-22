package com.example.police.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.swing.undo.StateEdit;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 20,message = "Слишком короткое заявление")
    private String text;
    @NotNull
    private boolean status;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private State state;
    @NotNull
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private User user;
    @Nullable
    @ManyToMany
    @JoinTable(name = "user_statement",
            joinColumns = @JoinColumn(name = "statement_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<User> users;

    public Statement(String text, boolean status, State state, User user){
        this.text = text;
        this.status = status;
        this.state = state;
        this.user = user;
    }

    public Statement(){

    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
