package com.linda.suphub.controllers;

import com.linda.suphub.dto.PostSumDetails;
import com.linda.suphub.services.StatisticsService;
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
public class StatisticsController {

    private final StatisticsService service;


    @GetMapping("/count/{user-id}")
    public ResponseEntity<Long> countPostsByUserIdd(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.countPostsByUserIdd(userId));
    }

    @GetMapping("/count-by-date/{user-id}")
    public ResponseEntity<List<PostSumDetails>> countPostsByUserIdAndDateRangee(
            @PathVariable("user-id") Integer userId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
    {
        return ResponseEntity.ok(service.countPostsByUserIdAndDateRangee(startDate, endDate, userId));
    }




}
