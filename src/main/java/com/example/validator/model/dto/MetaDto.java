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
public class MetaDto {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("from")
    private Integer from;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("path")
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("per_page")
    private Integer perPage;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("to")
    private Integer to;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("total")
    private Integer total;

}