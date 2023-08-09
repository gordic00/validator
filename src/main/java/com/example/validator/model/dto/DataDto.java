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
public class DataDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("link")
    private String link;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("platform_type")
    private String platformType;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("title")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("description")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("currency")
    private String currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("quantity")
    private String quantity;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("prices")
    private PricesDto prices;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("category")
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("images")
    private List<ImagesDto> images;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonProperty("videos")
    private List<ImagesDto> videos;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("screenshot")
    private String screenshot;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_name")
    private String sellerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_id")
    private String sellerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("seller_url")
    private String sellerUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("platform")
    private String platform;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("created_at")
    private String createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("updated_at")
    private String updatedAt;

}