package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myrmicatech.globalguidebackend.entity.ids.AccountLocationId;
import com.myrmicatech.globalguidebackend.enums.LocationLogType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "LOCATION_LOGS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationLog {

    @EmbeddedId
    private AccountLocationId accountLocationId;

    @Column(name = "TYPE")
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private LocationLogType locationLogType = LocationLogType.INSERT;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("LOCATION_ID")
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("USER_ID")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

}
