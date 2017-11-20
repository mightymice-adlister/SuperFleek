package com.mightymice.superfleek.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="brands")
public class Brand {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="brand")
    private List<Makeup> makeups;


    public Brand(String name) {
        this.name = name;
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
