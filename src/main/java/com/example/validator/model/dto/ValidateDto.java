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
public class ValidateDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("url")
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("additional")
    private String additional;

}