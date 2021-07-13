package com.waracle.cakemgr.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Cake {

    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private String image;
}
