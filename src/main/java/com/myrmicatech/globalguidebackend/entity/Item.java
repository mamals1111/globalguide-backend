package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEMS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DEFAULT_LEVEL")
    private Integer defaultLevel;

    @Column(name = "DEFAULT_ENERGY")
    @Builder.Default
    private Integer defaultEnergy = 15;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_FILE", referencedColumnName = "ID")
    private File icon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PHOTO_FILE", referencedColumnName = "ID")
    private File photo;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OwnedItem> ownedItems = new ArrayList<>();

}
