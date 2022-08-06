package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "COMMENTS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private UUID userId;

    @Column(name = "REVIEW_ID", insertable = false, updatable = false)
    private UUID reviewId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Review review;

}
