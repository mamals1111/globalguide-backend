package com.myrmicatech.globalguidebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Table(name = "TIMES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Time extends BaseEntity {

    @Column(name = "WEEK_DAY")
    private String weekDay;

    @Column(name = "OPEN_TIME")
    private LocalTime openTime;

    @Column(name = "CLOSE_TIME")
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Location location;

}
