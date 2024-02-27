package com.linda.suphub.repositories;

import com.linda.suphub.dto.PostSumDetails;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT COUNT(p) FROM Post p")
    Long countTotalPosts();


    @Query("SELECT p.postDate AS postDate, COUNT(p) AS nombre FROM Post p WHERE p.postDate BETWEEN :startDate AND :endDate GROUP BY p.postDate")
    List<PostSumDetails> countTotalPostsBetweenDates(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.status = 'RESERVED' WHERE p.id = ?1")
    void updateStatusToReserved(Integer postId);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.status = 'AVAILABLE' WHERE p.id = ?1")
    void updateStatusToAvailable(Integer postId);
}


