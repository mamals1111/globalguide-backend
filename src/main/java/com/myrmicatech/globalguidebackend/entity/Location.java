package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "LOCATIONS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "AVG_RATING")
    private double avgRating;

    @Column(name = "TOTAL_REVIEWS")
    private long totalReviews;

    @Column(name = "LATITUDE", precision = 20, scale = 15)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", precision = 20, scale = 15)
    private BigDecimal longitude;

    @Column(name = "AVATAR", insertable = false, updatable = false)
    private UUID avatar;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private UUID userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AVATAR", referencedColumnName = "ID")
    private File avatarFile;

    @OneToMany(mappedBy = "locationBanner", cascade = CascadeType.ALL)
    private List<File> bannerFiles = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<LocationLog> locationLogs = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "CATEGORIES", referencedColumnName = "ID")
    private List<Category> categories;
}
