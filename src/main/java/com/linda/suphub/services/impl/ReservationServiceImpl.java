package com.linda.suphub.services.impl;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.Reservation;
import com.linda.suphub.models.ReservationStatus;
import com.linda.suphub.models.User;
import com.linda.suphub.repositories.PostRepository;
import com.linda.suphub.repositories.ReservationRepository;
import com.linda.suphub.services.ReservationService;
import com.linda.suphub.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ObjectsValidator<ReservationDto> validator;
    @Override
    public Integer save(ReservationDto dto) {

        validator.validate(dto);
        Reservation reservation = ReservationDto.toEntity(dto);
        return repository.save(reservation).getId();

    }

    @Override
    public List<ReservationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());    }

    @Override
    public ReservationDto findById(Integer id) {
        return repository.findById(id)
                .map(ReservationDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No post was found with the ID: " + id));    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);


    }

    /*@Override
    public Integer reserveArticle(Integer userId, Integer postId) {
        return null;
    }

    @Override
    public Integer confirmReservation(Integer reservationId) {
        return null;
    }

    @Override
    public Integer cancelReservation(Integer reservationId) {
        return null;
    }

    @Override
    public Optional<Reservation> findAllByUserAndStatus(User user, ReservationStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> findByPost(Post post) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> findByPostAndStatus(Post post, ReservationStatus status) {
        return Optional.empty();
    }*/
}
