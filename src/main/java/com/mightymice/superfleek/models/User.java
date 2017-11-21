package com.mightymice.superfleek.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @NotBlank(message ="Users must have an email address")
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank(message ="Users must have a username")
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message ="You must enter a password")
    @Column(nullable = false)
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MakeupList> makeupLists;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Look> lookList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    //Default constructor (no arguments)
    public User(){
    }


    // this is the copy constructor so that the security works right
    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<MakeupList> getMakeupLists() {
        return makeupLists;
    }

    public void setMakeupLists(List<MakeupList> makeupLists) {
        this.makeupLists = makeupLists;
    }

    public List<Look> getLookList() {
        return lookList;
    }

    public void setLookList(List<Look> lookList) {
        this.lookList = lookList;
    }
}
