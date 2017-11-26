package com.mightymice.superfleek.models;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;

@Entity
public class MakeupType {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Makeup> makeupsWithType;

    public MakeupType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Makeup> getMakeupsWithType() {
        return makeupsWithType;
    }

    public void setMakeupsWithType(List<Makeup> makeupsWithType) {
        this.makeupsWithType = makeupsWithType;
    }
}
