package com.mightymice.superfleek.models;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="makeup")
public class Makeup {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(
            name = "makeup_categories",
            joinColumns = {@JoinColumn(name = "makeup_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @Column
    private String colorName;
    @ManyToOne
    @JoinColumn(name = "color_family_id")
    private ColorFamily colorFamily;
    @Column
    private String finish;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "makeup")
    private List<Review> reviews;
    @ManyToMany(mappedBy = "makeups")
    private List<Look> looks;
    @ManyToMany(mappedBy = "makeups")
    private List<MakeupList> makeupLists;

}