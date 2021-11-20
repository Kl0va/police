package com.example.police.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "codex")
public class Codex {
    @Id
    private String name;
    @OneToMany(mappedBy = "codex_name",fetch = FetchType.EAGER)
    Collection<State> states;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<State> getStates() {
        return states;
    }

    public void setStates(Collection<State> states) {
        this.states = states;
    }

}
