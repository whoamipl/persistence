package com.example.shdemo.domain;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "producer.all", query = "Select p from Producer p"),
        @NamedQuery(name = "producer.byId", query = "Select p from Producer p where p.id = :id"),
})

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public Producer(String name) {
        this.name = name;
    }

    public Producer(){};



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

