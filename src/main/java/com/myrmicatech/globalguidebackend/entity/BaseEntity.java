package com.myrmicatech.globalguidebackend.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private UUID id;

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
