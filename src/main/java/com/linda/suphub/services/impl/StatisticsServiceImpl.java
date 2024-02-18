package com.linda.suphub.services.impl;


import com.linda.suphub.dto.PostSumDetails;
import com.linda.suphub.repositories.PostRepository;
import com.linda.suphub.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final PostRepository repository;


    @Override
    public Long countTotalPostss() {
        return repository.countTotalPosts();
    }

    @Override
    public List<PostSumDetails> countTotalPostsBetweenDatess(LocalDate startDate, LocalDate endDate) {

       // LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
       // LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));

        return repository.countTotalPostsBetweenDates(startDate, endDate);
    }


}
