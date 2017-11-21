package com.mightymice.superfleek.models;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;

@Entity
public class MakeupType {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Makeup> makeupsWithType;
}
