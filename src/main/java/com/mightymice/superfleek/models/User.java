package com.mightymice.superfleek.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue
    @JsonBackReference
    private Long id;
    @NotBlank(message ="You must enter an email address")
    @Column(nullable = false, unique = true)
    @Email
    @JsonBackReference
    private String email;
    @NotBlank(message ="You must enter a username")
    @Column(nullable = false, unique = true)
    @JsonBackReference
    private String username;
    @NotBlank(message ="You must enter a password")
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column
    @JsonBackReference
    private String firstName;
    @Column
    @JsonBackReference
    private String lastName;
    @Column(columnDefinition = "text")
    @JsonBackReference
    private String bio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<MakeupList> makeupLists;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Look> lookList;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;
    @NotBlank(message = "You must confirm your password")
    @Transient
    @JsonIgnore
    private String confirmPassword;

//    public String getProfilePicUrl() {
//        return profilePicUrl;
//    }
//
//    public void setProfilePicUrl(String profilePicUrl) {
//        this.profilePicUrl = profilePicUrl;
//    }
//
//    @Transient
//    @JsonIgnore
//    private String profilePicUrl = getProfilePic().getPictureFilePath();
@JsonBackReference
    private boolean hasLoggedIn;

    public boolean isHasLoggedIn() {
        return hasLoggedIn;
    }

    public void setHasLoggedIn(boolean hasLoggedIn) {
        this.hasLoggedIn = hasLoggedIn;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

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
        bio = copy.bio;
        lookList = copy.lookList;
        makeupLists = copy.makeupLists;
        hasLoggedIn = copy.hasLoggedIn;
        reviews = copy.reviews;

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public Look getProfilePic(){
        Look profilePic = new Look();
        for(Look look: lookList){
            if(look.isProfilePic()){
                profilePic = look;
            }
        }
            return profilePic;
    }
    public String getProfilePicUrl(){
        String url = "";
        url = getProfilePic().getPictureFilePath();
        return url;
    }
    public MakeupList getCollectionFromMakeupList(){
        MakeupList collection = new MakeupList();
        for(MakeupList list: makeupLists){
            if(list.getTitle().equals("Collection")){
                collection=list;
                System.out.println("found the list");
            }
            System.out.println("found a list, but it's not named collection");
        }
        return collection;
    }
}
