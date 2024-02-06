package com.linda.suphub.repositories;

import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query("SELECT p FROM Post p WHERE p.itemCategory = :category")
    List<Post> findAllByItemCategory (@Param("category") PostCategory category);

    @Query("SELECT p FROM Post p WHERE p.status = :status")
    List<Post> findAllByStatus (@Param("status") PostStatus status);

    @Query("SELECT p FROM Post p WHERE p.user = :user")
    List<Post> findAllByUser (@Param("user") User user);



}
