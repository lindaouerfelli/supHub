package com.linda.suphub.controllers;

import com.linda.suphub.dto.PostSumDetails;
import com.linda.suphub.services.StatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Tag(name = "statistics")
public class StatisticsController {

    private final StatisticsService service;


    @GetMapping("/count")
    public ResponseEntity<Long> countTotalPostss() {
        return ResponseEntity.ok(service.countTotalPostss());
    }

    @GetMapping("/count-by-date")
    public ResponseEntity<List<PostSumDetails>> countTotalPostsBetweenDatess(
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
    {
        return ResponseEntity.ok(service.countTotalPostsBetweenDatess(startDate, endDate));
    }



}
