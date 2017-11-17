package com.mightymice.superfleek.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="categories")
public class Category {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Makeup> makeups;

    public Category() {

    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String name) {
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
