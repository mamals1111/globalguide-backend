package com.myrmicatech.globalguidebackend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myrmicatech.globalguidebackend.entity.ids.AccountItemId;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "OWNED_ITEMS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnedItem {

    @EmbeddedId
    private AccountItemId accountItemId;

    @Column(name = "HASH_ID")
    private UUID hashId;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "ENERGY")
    @Builder.Default
    private Integer energy = 10;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("USER_ID")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ITEM_ID")
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private Item item;

    @OneToMany(mappedBy = "ownedItem", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();
}
