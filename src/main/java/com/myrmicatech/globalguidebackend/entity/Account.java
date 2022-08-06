package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myrmicatech.globalguidebackend.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {
    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ROLE")
    @Builder.Default
    private RoleType role = RoleType.USER;

    @Column(name = "TOKEN")
    private double token;

    @Column(name = "AVATAR", insertable = false, updatable = false)
    private UUID avatar;

    @Column(name = "TOTAL_FOLLOWERS")
    @Builder.Default
    private double totalFollowers = 0;

    @Column(name = "TOTAL_FOLLOWINGS")
    @Builder.Default
    private double totalFollowings = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AVATAR", referencedColumnName = "ID")
    private File avatarFile;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Otp otp;

//    @OneToMany(mappedBy = "followers", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
//    @JsonManagedReference
//    protected List<Follower> followers;
//
//    @OneToMany(mappedBy = "followings", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
//    @JsonManagedReference
//    protected List<Follower> followings;

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="EMPLOYEE_COLLEAGUE",
            joinColumns={@JoinColumn(name="EMPLOYEE_ID")},
            inverseJoinColumns={@JoinColumn(name="COLLEAGUE_ID")})
    @JsonIgnore
    private List<Account> followers = new ArrayList<>();

    @ManyToMany(mappedBy="followers")
    @JsonIgnore
    private List<Account> followings = new ArrayList<>();


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<OwnedItem> ownedItems = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<LocationLog> locationLogs = new ArrayList<>();
}
