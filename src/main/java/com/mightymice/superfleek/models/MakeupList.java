package com.mightymice.superfleek.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="makeup_lists")
public class MakeupList {
    @Id
    @GeneratedValue
    @JsonBackReference
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @ManyToMany(cascade = ALL)
    @JoinTable(
            name="makeups_on_makeup_lists",
            joinColumns={@JoinColumn(name="makeup_list_id")},
            inverseJoinColumns = {@JoinColumn(name="makeup_id")}
    )
    @JsonBackReference
    private List<Makeup> makeups;

    public MakeupList() {
    }
    public MakeupList(String title){
        this.title=title;
    }
    public MakeupList(String title, User user){
        this.title= title;
        this.user=user;
    }

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

    public List<Makeup> getMakeups() {
        return makeups;
    }

    public void setMakeups(List<Makeup> makeups) {
        this.makeups = makeups;
    }
    public boolean hasMakeup(Makeup makeup){
        for(Makeup makeupInMakeupList: makeups){
            if(makeup == makeupInMakeupList) {
                return true;
            }
        }
        return false;
    }
}
