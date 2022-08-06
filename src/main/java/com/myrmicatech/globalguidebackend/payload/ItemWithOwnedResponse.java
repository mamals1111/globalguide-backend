package com.myrmicatech.globalguidebackend.payload;

import com.myrmicatech.globalguidebackend.entity.File;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemWithOwnedResponse {

    private String name;

    private File icon;

    private File photo;

    @Builder.Default
    private boolean owned = false;
}
