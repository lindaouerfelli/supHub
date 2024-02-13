package com.linda.suphub.repositories;

import com.linda.suphub.models.Post;
import com.linda.suphub.models.Reservation;
import com.linda.suphub.models.ReservationStatus;
import com.linda.suphub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


   /* Optional<Reservation> findAllByUserAndStatus(User user, ReservationStatus status);

    Optional<Reservation> findByPost (Post post); // pour voir les détails de réservation liée a un post

    Optional<Reservation> findByPostAndStatus(Post post, ReservationStatus status);*/

    @Query("SELECT p FROM Reservation p WHERE p.user = :user")
    List<Reservation> findAllByUser (@Param("user") User user);

}

