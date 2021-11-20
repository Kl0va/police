package com.example.police.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "state")
public class State {
    @Id
    private Long code;
    private String text;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Codex codex_name;

    @OneToMany(mappedBy = "state",fetch = FetchType.EAGER)
    Collection<Statement> statements;

    public State(String text, Codex codex_name){
        this.text = text;
        this.codex_name = codex_name;
    }
    
    public State(){

    }

    public Collection<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Collection<Statement> statements) {
        this.statements = statements;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Codex getCodex_name() {
        return codex_name;
    }

    public void setCodex_name(Codex codex_name) {
        this.codex_name = codex_name;
    }
}
