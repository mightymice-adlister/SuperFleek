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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colorFamily")
    private List<Makeup> makeups;
}
