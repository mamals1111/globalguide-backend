package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "REVIEWS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "IS_PHOTO_VERIFIED")
    @Builder.Default
    private boolean isPhotoVerified = false;

    @Column(name = "IS_REVIEW_VERIFIED")
    @Builder.Default
    private boolean isReviewVerified = false;

    @Column(name = "IS_LOCATION_VERIFIED")
    @Builder.Default
    private boolean isLocationVerified = false;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private UUID userId;

    @Column(name = "LOCATION_ID", insertable = false, updatable = false)
    private UUID locationId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
    private Location location;

    @ManyToOne
    @JsonManagedReference
    private OwnedItem ownedItem;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<File> reviewFiles = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
