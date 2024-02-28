package com.linda.suphub.services.impl;


import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.models.*;
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
    private final PostRepository postRepository;
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

    @Override
    public List<ReservationDto> findAllByUser(User user) {
        return repository.findAllByUser(user)
                .stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationByPostId(Integer id) {
        return repository.getReservationByPostId(id)
                .map(ReservationDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No post was found with the ID: " + id));

    }

}


