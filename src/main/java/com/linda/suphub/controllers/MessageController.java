package com.linda.suphub.controllers;


import com.linda.suphub.dto.MessageDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

        private final MessageService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody MessageDto messageDto) {

        return ResponseEntity.ok(service.save(messageDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<MessageDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{message-id}")
    public ResponseEntity<MessageDto> findById(@PathVariable("message-id") Integer messageDto) {
        return ResponseEntity.ok(service.findById(messageDto));
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<MessageDto>> findAllByUserId(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{message-id}")
    public ResponseEntity<Void> delete(@PathVariable("message-id") Integer messageDto) {
        service.delete(messageDto);
        return ResponseEntity.accepted().build();
    }



}
