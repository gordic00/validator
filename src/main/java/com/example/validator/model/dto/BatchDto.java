package com.example.validator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class BatchDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("check_field")
    private String checkField;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("brand")
    private String brand;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("project_id")
    private String projectId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("published_at")
    private String publishedAt;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("links")
    private LinksDto links;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("meta")
    private MetaDto meta;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("data_count")
    private Integer dataCount;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("data")
    private List<DataDto> data;
}