package com.linda.suphub.services;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.Reservation;
import com.linda.suphub.models.ReservationStatus;
import com.linda.suphub.models.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService extends AbstractService<ReservationDto>{

    /*// Méthode pour réserver un article
    Integer reserveArticle(Integer userId, Integer postId);

    // Méthode pour confirmer une réservation
    Integer confirmReservation(Integer reservationId);

    // Méthode pour annuler une réservation
    Integer cancelReservation(Integer reservationId);

    Optional<Reservation> findAllByUserAndStatus(User user, ReservationStatus status);

    Optional<Reservation> findByPost (Post post); // pour voir les détails de réservation liée a un post

    Optional<Reservation> findByPostAndStatus(Post post, ReservationStatus status);*/

    List<ReservationDto> findAllByUser (User user);

    ReservationDto getReservationByPostId(Integer id);

}
