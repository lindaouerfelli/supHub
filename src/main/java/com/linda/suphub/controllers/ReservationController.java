package com.linda.suphub.controllers;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.ReservationDto;
import com.linda.suphub.models.User;
import com.linda.suphub.services.PostService;
import com.linda.suphub.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ReservationDto reservationDto) {

        return ResponseEntity.ok(service.save(reservationDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{reservation-id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable("reservation-id") Integer reservationDto) {
        return ResponseEntity.ok(service.findById(reservationDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDto>> getAllPostsByUser(@PathVariable ("userId") Integer userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(service.findAllByUser(user));
    }

    @DeleteMapping("/{reservation-id}")
    public ResponseEntity<Void> delete(@PathVariable("reservation-id") Integer reservationDto) {
        service.delete(reservationDto);
        return ResponseEntity.accepted().build();
    }

}
