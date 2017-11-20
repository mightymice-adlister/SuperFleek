package com.mightymice.superfleek.models;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="looks")
public class Look {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(nullable = false)
    private String pictureFilePath;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="looks_makeup",
            joinColumns = {@JoinColumn(name="look_id")},
            inverseJoinColumns = {@JoinColumn(name="makeup_id")}
    )
    private List<Makeup> makeups;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(nullable = false)
    private boolean isProfilePic;

}
