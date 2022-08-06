package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CATEGORIES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ICON_URL")
    private String iconURL;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Location> locations;
}
