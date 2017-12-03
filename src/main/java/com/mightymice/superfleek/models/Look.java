package com.mightymice.superfleek.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="looks")
public class Look {
    @Id
    @GeneratedValue
    @JsonBackReference
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;
    @Column(nullable = false)
    private String pictureFilePath;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="looks_makeup",
            joinColumns = {@JoinColumn(name="look_id")},
            inverseJoinColumns = {@JoinColumn(name="makeup_id")}
    )
    @JsonBackReference
    private List<Makeup> makeups;
    @Column(nullable = true)
    private String title;

    @Column
    private String description;
    @Column(nullable = false)
    private boolean isProfilePic;



    public Look(){};





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    public void setPictureFilePath(String pictureFilePath) {
        this.pictureFilePath = pictureFilePath;
    }

    public List<Makeup> getMakeups() {
        return makeups;
    }

    public void setMakeups(List<Makeup> makeups) {
        this.makeups = makeups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProfilePic() {
        return isProfilePic;
    }

    public void setProfilePic(boolean profilePic) {
        isProfilePic = profilePic;
    }


}
