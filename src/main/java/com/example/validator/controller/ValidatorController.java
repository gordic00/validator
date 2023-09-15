package com.example.validator.controller;

import com.example.validator.model.ScreenshotResponse;
import com.example.validator.model.SiteData;
import com.example.validator.model.dto.BatchDto;
import com.example.validator.model.dto.ValidateListDto;
import com.example.validator.service.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/")
@RequiredArgsConstructor
public class ValidatorController {
    private final ValidatorService service;

    @PostMapping(path = "/batch-checker")
    public ResponseEntity<BatchDto> checkBatchIdFields(
            @RequestBody BatchDto batchDto
    ) {
        return service.checkBatchIdFields(batchDto);
    }

    @PostMapping(path = "/batch-checker-by-field")
    public ResponseEntity<List<String>> checkBatchIdFieldsByField(
            @RequestBody BatchDto batchDto
    ) {
        return service.checkBatchIdFieldsByField(batchDto);
    }

    @PostMapping(path = "/batch-data-check")
    public ResponseEntity<ValidateListDto> checkBatchIdByFields(
            @RequestBody BatchDto batchDto
    ) {
        return service.checkBatchData(batchDto);
    }

    @GetMapping(path = "/scrape")
    public ResponseEntity<?> scrape(
            @RequestParam("url") String url
    ) throws IOException {
        return service.scrape(url);
    }

    @GetMapping(path = "/screenshot")
    public ResponseEntity<List<ScreenshotResponse>> getScreenshot(
            @RequestParam("ip") String ip,
            @RequestParam("urls") List<String> urls
    ) {
        return service.getScreenshot(ip, urls);
    }

}
