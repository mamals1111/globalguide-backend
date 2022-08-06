package com.myrmicatech.globalguidebackend.entity.ids;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@Builder
public class AccountItemId implements Serializable {

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "ITEM_ID")
    private UUID itemId;
}
