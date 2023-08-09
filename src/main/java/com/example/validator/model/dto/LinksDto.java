package com.example.validator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class LinksDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("first")
    private String first;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("last")
    private String last;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("prev")
    private String prev;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("next")
    private String next;

}