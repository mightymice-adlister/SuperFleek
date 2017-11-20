package com.mightymice.superfleek.models;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="makeup_lists")
public class MakeupList {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id")
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
    private List<Makeup> makeups;

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
}
