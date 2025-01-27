package com.gardenia.viveroapp.DTO;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDTO {

    @NonNull
    private Integer docNumber;

    @NonNull
    private String docType;

    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private Long phoneNumber;
}
