package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myrmicatech.globalguidebackend.enums.OtpStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "OTPS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Otp {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "IS_USED")
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private OtpStatus otpStatus = OtpStatus.USED;

    @Column(name = "EXPIRY_TIME")
    @Builder.Default
    private LocalDateTime expiryTime = LocalDateTime.now();

    @Column(name = "OTP")
    private String otp;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private Account account;

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
