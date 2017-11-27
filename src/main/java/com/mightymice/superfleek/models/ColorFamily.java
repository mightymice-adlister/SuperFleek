package com.mightymice.superfleek.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "color_family")
public class ColorFamily {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String hex;
    @ManyToMany( mappedBy = "colorFamily")
    private List<Makeup> makeups;

    public ColorFamily() {
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

    public List<Makeup> getMakeups() {
        return makeups;
    }

    public void setMakeups(List<Makeup> makeups) {
        this.makeups = makeups;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
