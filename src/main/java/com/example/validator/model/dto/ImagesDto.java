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
public class ImagesDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("source_url")
    private String sourceUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("download_url")
    private String downloadUrl;

}