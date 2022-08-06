package com.myrmicatech.globalguidebackend.entity.ids;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AccountLocationId implements Serializable {

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "LOCATION_ID")
    private UUID locationId;

}
