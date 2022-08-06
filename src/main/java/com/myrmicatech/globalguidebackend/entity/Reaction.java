package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myrmicatech.globalguidebackend.entity.ids.AccountReviewId;
import com.myrmicatech.globalguidebackend.enums.ReactionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "REACTIONS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    @EmbeddedId
    private AccountReviewId accountReviewId;

    @Column(name = "REACTION_TYPE")
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private ReactionType reactionType = ReactionType.LIKE;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("USER_ID")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("REVIEW_ID")
    @JoinColumn(name = "REVIEW_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Review review;

    @Column(name = "IS_DELETED")
    private boolean isDeleted = false;
    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;
    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedAt;
    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;
}
