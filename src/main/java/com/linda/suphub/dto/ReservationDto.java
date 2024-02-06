package com.linda.suphub.dto;

import com.linda.suphub.models.Post;
import com.linda.suphub.models.Reservation;
import com.linda.suphub.models.ReservationStatus;
import com.linda.suphub.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReservationDto {

    private Integer id;
    private LocalDateTime reservationDate;
    private ReservationStatus status;

    private Integer userId;
    private Integer postId;


    public static ReservationDto fromEntity(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus())
                .userId(reservation.getUser().getId())
                .postId(reservation.getPost().getId())
                .build();
    }

    public static Reservation toEntity(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .reservationDate(reservationDto.getReservationDate())
                .status(reservationDto.getStatus())
                .user(User.builder().id(reservationDto.getUserId()).build())
                .post(Post.builder().id(reservationDto.getPostId()).build())
                .build();
    }





}
