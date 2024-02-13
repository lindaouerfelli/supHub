package com.linda.suphub.services;

import com.linda.suphub.dto.PostSumDetails;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    Long countPostsByUserIdd(Integer userId);

    List<PostSumDetails> countPostsByUserIdAndDateRangee( LocalDate startDate, LocalDate endDate,Integer userId);
}
