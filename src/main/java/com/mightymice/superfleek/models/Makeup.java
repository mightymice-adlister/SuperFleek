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
    @ManyToMany
    @JoinTable(
            name = "makeup_color_family",
            joinColumns = {@JoinColumn(name = "makeup_id")},
            inverseJoinColumns = {@JoinColumn(name = "color_family_id")}
    )
    private List<ColorFamily> colorFamily;
    @Column
    private String finish;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "makeup")
    private List<Review> reviews;
    @ManyToMany(mappedBy = "makeups")
    private List<Look> looks;
    @ManyToMany(mappedBy = "makeups")
    private List<MakeupList> makeupLists;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private MakeupType type;
    private String thumbnailUrl;

    public Makeup() {
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public List<ColorFamily> getColorFamily() {
        return colorFamily;
    }

    public void setColorFamily(List<ColorFamily> colorFamily) {
        this.colorFamily = colorFamily;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Look> getLooks() {
        return looks;
    }

    public void setLooks(List<Look> looks) {
        this.looks = looks;
    }

    public List<MakeupList> getMakeupLists() {
        return makeupLists;
    }

    public void setMakeupLists(List<MakeupList> makeupLists) {
        this.makeupLists = makeupLists;
    }

    public MakeupType getType() {
        return type;
    }

    public void setType(MakeupType type) {
        this.type = type;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}