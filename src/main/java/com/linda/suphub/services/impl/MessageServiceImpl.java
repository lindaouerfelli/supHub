package com.linda.suphub.services.impl;

import com.linda.suphub.dto.MessageDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.models.Message;
import com.linda.suphub.models.Reservation;
import com.linda.suphub.repositories.MessageRepository;
import com.linda.suphub.repositories.ReservationRepository;
import com.linda.suphub.services.MessageService;
import com.linda.suphub.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final ObjectsValidator<MessageDto> validator;
    @Override
    public Integer save(MessageDto dto) {

        validator.validate(dto);
        Message message = MessageDto.toEntity(dto);
        return repository.save(message).getId();    }

    @Override
    public List<MessageDto> findAll() {
        return repository.findAll()
                .stream()
                .map(MessageDto::fromEntity)
                .collect(Collectors.toList());      }

    @Override
    public MessageDto findById(Integer id) {
        return repository.findById(id)
                .map(MessageDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No post was found with the ID: " + id));    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);


    }
}


