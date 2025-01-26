package com.gardenia.viveroapp.Model.DTO;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
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
