package com.example.validator.service;

import com.example.validator.model.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidatorService {

    private final List<String> availableCurrencies =
            List.of("EUR", "BAM", "HK", "GBP", "TRY", "RSD", "RUB", "JPY", "THB", "MYR", "CNY", "PLN", "MXN",
                    "IDR", "BRL", "HUF", "RON", "ETH", "WETH", "BTC", "BNB", "FLOW", "COP", "RARI", "TARTR", "MATIC",
                    "USD", "BGN");

    public ResponseEntity<BatchDto> checkBatchIdFields(BatchDto batchDto) {
        BatchDto response = new BatchDto();
        validateBatchDto(response, batchDto);
        validateLinks(response, batchDto.getLinks());
        validateMeta(response, batchDto.getMeta());
        validateData(response, batchDto.getData());
        response.setDataCount(batchDto.getData().size());
        return ResponseEntity.ok(response);
    }

    private void validateData(BatchDto response, List<DataDto> data) {
        List<DataDto> invalidProducts = new LinkedList<>();
        response.setData(invalidProducts);
        if (data != null) {
            for (DataDto product : data) {
                DataDto invalidProduct = new DataDto();
                invalidProduct.setLink(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");

                if (!fieldContainsData(product.getPlatformType())) {
                    invalidProduct.setPlatformType("No data.");
                }
                if (!fieldContainsData(product.getTitle())) {
                    invalidProduct.setTitle("No data.");
                }
                if (!fieldContainsData(product.getDescription())) {
                    invalidProduct.setDescription("No data.");
                }
                if (!fieldContainsData(product.getCurrency())) {
                    invalidProduct.setCurrency("No data. " + product.getLink());
                } else {
                    validateCurrency(invalidProduct, product.getCurrency());
                }
                if (!fieldContainsData(product.getQuantity())) {
                    invalidProduct.setQuantity("No data.");
                }
                if (product.getPrices() == null) {
                    PricesDto prices = new PricesDto();
                    invalidProduct.setPrices(prices);
                } else {
                    validatePrices(product.getPrices());
                    invalidProduct.setPrices(product.getPrices());
                }
                if (!fieldContainsData(product.getCategory())) {
                    invalidProduct.setCategory("No data.");
                }
                invalidProduct.setImages(validateImages(product));
                invalidProduct.setVideos(validateImages(product));

                if (!fieldContainsData(product.getScreenshot())) {
                    invalidProduct.setScreenshot("No data. " + product.getLink());
                }
                if (!fieldContainsData(product.getSellerName())) {
                    invalidProduct.setSellerName("No data. " + product.getLink());
                }
                if (!fieldContainsData(product.getSellerId())) {
                    invalidProduct.setSellerId("No data.");
                }
                if (!fieldContainsData(product.getSellerUrl())) {
                    invalidProduct.setSellerUrl("No data.  " + product.getLink());
                }
                if (!fieldContainsData(product.getPlatform())) {
                    invalidProduct.setPlatform("No data.");
                }
                if (!fieldContainsData(product.getCreatedAt())) {
                    invalidProduct.setCreatedAt("No data.");
                }
                if (!fieldContainsData(product.getUpdatedAt())) {
                    invalidProduct.setUpdatedAt("No data.");
                }
                invalidProducts.add(invalidProduct);
            }
        }
    }

    private List<ImagesDto> validateImages(DataDto product) {
        List<ImagesDto> invalidImages = new LinkedList<>();
        ImagesDto invalidImage = new ImagesDto();
        if (product.getImages() != null) {
            for (ImagesDto image : product.getImages()) {
                invalidImage.setSourceUrl(fieldContainsData(
                        image.getSourceUrl()) ? image.getSourceUrl() : "No data. " + product.getTitle() + " " + product.getLink());
                invalidImage.setDownloadUrl(fieldContainsData(
                        image.getDownloadUrl()) ? image.getDownloadUrl() : "No data. " + product.getTitle() + " " + product.getLink());
                invalidImages.add(invalidImage);
            }
            return invalidImages;
        }
        invalidImage.setSourceUrl("No data " + product.getTitle() + " " + product.getLink());
        invalidImage.setDownloadUrl("No data " + product.getTitle() + " " + product.getLink());
        invalidImages.add(invalidImage);
        return invalidImages;
    }

    private void validatePrices(PricesDto prices) {
        if (prices != null) {
            if (!fieldContainsData(prices.getPrice())) {
                prices.setPrice("No data.");
            }
            if (!fieldContainsData(prices.getMinPrice())) {
                prices.setMinPrice("No data.");
            }
            if (!fieldContainsData(prices.getMaxPrice())) {
                prices.setMaxPrice("No data.");
            }
            if (!fieldContainsData(prices.getListPrice())) {
                prices.setListPrice("No data.");
            }
            if (!fieldContainsData(prices.getListMinPrice())) {
                prices.setListMinPrice("No data.");
            }
            if (!fieldContainsData(prices.getListMaxPrice())) {
                prices.setListMaxPrice("No data.");
            }
        }
    }

    private void validateCurrency(DataDto invalidProduct, String currency) {
        invalidProduct.setCurrency(availableCurrencies.contains(currency) ? currency : "NAT VALID -" + currency);
    }

    private void validateMeta(BatchDto response, MetaDto meta) {
        if (meta != null) {
            MetaDto errorMeta = new MetaDto();
            response.setMeta(errorMeta);
            if (!fieldContainsData(meta.getCurrentPage())) {
                response.getMeta().setCurrentPage(0);
            }
            if (!fieldContainsData(meta.getFrom())) {
                response.getMeta().setFrom(0);
            }
            if (!fieldContainsData(meta.getLastPage())) {
                response.getMeta().setLastPage(0);
            }
            if (!fieldContainsData(meta.getPerPage())) {
                response.getMeta().setPerPage(0);
            }
            if (!fieldContainsData(meta.getTo())) {
                response.getMeta().setTo(0);
            }
            if (!fieldContainsData(meta.getTotal())) {
                response.getMeta().setTotal(0);
            }
            if (!fieldContainsData(meta.getPath())) {
                response.getMeta().setPath("No data.");
            }
        }
    }

    private void validateLinks(BatchDto response, LinksDto links) {
        if (links != null) {
            LinksDto errorLinks = new LinksDto();
            response.setLinks(errorLinks);
            if (!fieldContainsData(links.getFirst())) {
                response.getLinks().setFirst("No data.");
            }
            if (!fieldContainsData(links.getLast())) {
                response.getLinks().setLast("No data.");
            }
            if (!fieldContainsData(links.getPrev())) {
                response.getLinks().setPrev("No data.");
            }
            if (!fieldContainsData(links.getNext())) {
                response.getLinks().setNext("No data.");
            }
        }
    }

    private void validateBatchDto(BatchDto response, BatchDto batchDto) {
        if (!fieldContainsData(batchDto.getBrand())) {
            response.setBrand("No data.");
        }
        if (!fieldContainsData(batchDto.getProjectId())) {
            response.setProjectId("No data.");
        }
        if (!fieldContainsData(batchDto.getPublishedAt())) {
            response.setPublishedAt("No data.");
        }
    }

    private <T> Boolean fieldContainsData(T brand) {
        return brand != null && !brand.equals("");
    }

    public ResponseEntity<List<String>> checkBatchIdFieldsByField(BatchDto batchDto) {
        List<String> responseList = new LinkedList<>();

        return ResponseEntity.ok(validateDataByField(responseList, batchDto));
    }

    private List<String> validateDataByField(List<String> response, BatchDto batchDto) {
        if (batchDto.getData() != null) {
            for (DataDto product : batchDto.getData()) {
                DataDto invalidProduct = new DataDto();
                String link = fieldContainsData(product.getLink()) ? product.getLink() : "No data.";

                switch (batchDto.getCheckField()) {
                    case "currency":
                        if (!fieldContainsData(product.getCurrency()) || !availableCurrencies.contains(product.getCurrency())) {
                            response.add(product.getCurrency() + " : " + link);
                        }
                        break;
                    case "seller_name":
                        if (!fieldContainsData(product.getSellerName())) {
                            response.add(link);
                        }
                        break;
                    case "seller_url":
                        if (!fieldContainsData(product.getSellerUrl())) {
                            response.add(link);
                        }
                        break;
                    case "seller_id":
                        if (!fieldContainsData(product.getSellerId())) {
                            response.add(link);
                        }
                        break;
                    case "title":
                        if (!fieldContainsData(product.getTitle()) || product.getTitle().isBlank()) {
                            response.add(link);
                        }
                        break;
                    case "link":
                        if (!fieldContainsData(product.getLink()) || !product.getLink().startsWith("https:")) {
                            response.add(link);
                        }
                        break;
                    case "image":
                        if (product.getImages() == null) {
                            response.add(link);
                        } else {
                            for (ImagesDto image : product.getImages()) {
                                if (!fieldContainsData(image.getSourceUrl())) {
                                    response.add(link);
                                }
                            }
                        }
                        break;
                }
            }
        }
        HashSet<Object> unique = new LinkedHashSet<>();
        response.removeIf(aa -> !unique.add(aa));
        return response;
    }

    public ResponseEntity<ValidateListDto> checkBatchData(BatchDto batchDto) {
        if (batchDto.getData() != null) {
            ValidateListDto list = new ValidateListDto();
            for (DataDto product : batchDto.getData()) {
                String link = fieldContainsData(product.getLink()) ? product.getLink() : "No data.";

                DataDto invalidProduct = new DataDto();

                //currency
                if (!fieldContainsData(product.getCurrency()) || !availableCurrencies.contains(product.getCurrency())) {
                    ValidateDto currency = new ValidateDto();
                    currency.setUrl(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");
                    currency.setAdditional(product.getCurrency());
                    list.getCurrency().add(currency);
                }
                //seller name
                if (!fieldContainsData(product.getSellerName())) {
                    list.getSellerName().add(link);
                }
                //seller url
                if (!fieldContainsData(product.getSellerUrl())) {
                    list.getSellerUrl().add(link);
                }
                //seller id
                if (!fieldContainsData(product.getSellerId())) {
                    list.getSellerId().add(link);
                }
                //title
                if (!fieldContainsData(product.getTitle()) || product.getTitle().isBlank()) {
                    list.getTitle().add(link);
                }
                //link
                if (!fieldContainsData(product.getLink()) || !product.getLink().startsWith("https:")) {
                    list.getProductLink().add(link);
                }
                //images
                if (product.getImages() == null) {
                    ValidateDto image = new ValidateDto();
                    image.setUrl(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");
                    image.setAdditional("there are no images");
                    list.getImage().add(image);
                } else {
                    if (product.getImages().size() == 1) {
                        ValidateDto image2 = new ValidateDto();
                        image2.setUrl(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");
                        image2.setAdditional("Only one picture");
                        list.getImage().add(image2);
                        if (!fieldContainsData(product.getImages().get(0).getSourceUrl())) {
                            ValidateDto image1 = new ValidateDto();
                            image1.setUrl(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");
                            image1.setAdditional("Only one picture - and not valid");
                            list.getImage().add(image1);
                        }
                    }
                    if (product.getImages().size() > 1) {
                        for (ImagesDto image : product.getImages()) {
                            if (!fieldContainsData(image.getSourceUrl())) {
                                ValidateDto image1 = new ValidateDto();
                                image1.setUrl(fieldContainsData(product.getLink()) ? product.getLink() : "No data.");
                                image1.setAdditional("product has more pictures - check!");
                                list.getImage().add(image1);
                            }
                        }
                    }
                }
            }
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> scrape(String url) throws IOException {
        ObjectMapper om = new ObjectMapper();
        String html = Jsoup.connect(url).get().html();
        Document doc = Jsoup.parse(html);
        Elements gallery = doc.select("div.gallery-root-n3_HK");
        for (Element image : gallery) {
            image.attr("width", "200px");
            image.attr("height", "200px");
            System.out.println(image);
        }
//        Elements images = doc.getElementsByClass("images-preview-previewImageWrapper-RfThd");
//        System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(images));

        return null;
    }
}
