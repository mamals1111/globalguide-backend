package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "FILES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File extends BaseEntity {

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CHECK_SUM")
    private String checkSum;

    @Column(name = "FILE_DOWNLOAD_URI")
    private String fileDownloadUri;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_SIZE")
    private Long size;

    @Column(name = "LOCATION_BANNER_ID", insertable = false, updatable = false)
    private UUID locationBannerId;

    @Column(name = "REVIEW_ID", insertable = false, updatable = false)
    private UUID reviewId;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "LOCATION_BANNER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Location locationBanner;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Review review;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

    @OneToOne(mappedBy = "avatarFile")
    @JsonIgnore
    private Account accountAvatar;

    @OneToOne(mappedBy = "avatarFile")
    @JsonIgnore
    private Location locationAvatar;

    @OneToOne(mappedBy = "icon")
    @JsonIgnore
    private Item itemIcon;

    @OneToOne(mappedBy = "photo")
    @JsonIgnore
    private Item itemPhoto;
}
