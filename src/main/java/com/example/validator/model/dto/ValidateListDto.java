package com.example.validator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidateListDto {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("currency")
    private List<ValidateDto> currency = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_name")
    private List<String> sellerName = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_url")
    private List<String> sellerUrl = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_id")
    private List<String> sellerId = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("title")
    private List<String> title = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("product_link")
    private List<String> productLink = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("price")
    private List<String> price = new LinkedList<>();

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("image")
    private List<ValidateDto> image = new LinkedList<>();
}