package com.linda.suphub.repositories;

import com.linda.suphub.dto.PostSumDetails;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query("SELECT p FROM Post p WHERE p.itemCategory = :category")
    List<Post> findAllByItemCategory (@Param("category") PostCategory category);

    @Query("SELECT p FROM Post p WHERE p.status = :status")
    List<Post> findAllByStatus (@Param("status") PostStatus status);

    @Query("SELECT p FROM Post p WHERE p.user = :user")
    List<Post> findAllByUser (@Param("user") User user);


    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId AND p.createdDate BETWEEN :startDate AND :endDate")
    Long countPostsByUserIdAndDateRange(@Param("userId") Integer userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    Long countPostsByUserId(@Param("userId") Integer userId);

    @Query("SELECT p.postDate AS postDate, COUNT(p) AS amount FROM Post p WHERE p.user.id = :userId AND p.postDate BETWEEN :startDate AND :endDate GROUP BY p.postDate")
    List<PostSumDetails> countPostsByUserIdAndDateRange(@Param("userId") Integer userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
