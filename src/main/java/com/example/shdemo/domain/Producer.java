package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.List;

public class Producer {
 @Id
 @GeneratedValue( strategy = GenerationType.IDENTITY)
 private long id;

 private String name;
 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 private List<Computer> producedComputers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

