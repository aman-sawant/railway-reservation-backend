package com.railway.reservation.controller;

import com.railway.reservation.dto.BookingRequest;
import com.railway.reservation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Validated
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    public ResponseEntity<String> bookTicket(@RequestBody BookingRequest request){
        String response= bookingService.bookTicket(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId){
        String response= bookingService.cancelTicket(bookingId);
        return ResponseEntity.ok(response);
    }
}
