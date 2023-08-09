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
public class PricesDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("price")
    private String price;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("min_price")
    private String minPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("max_price")
    private String maxPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("list_price")
    private String listPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("list_min_price")
    private String listMinPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("list_max_price")
    private String listMaxPrice;
}