package com.example.police.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "position")
public class Position {
    @Id
    private String name;
    private double salary;
    @OneToMany(mappedBy = "position",fetch = FetchType.EAGER)
    Collection<User> users;

    public Position(String name, double salary){
        this.name = name;
        this.salary = salary;
    }
    public Position(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
