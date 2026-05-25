package com.railway.reservation.service;

import com.railway.reservation.dto.BookingRequest;
import com.railway.reservation.dto.BookingResponse;
import com.railway.reservation.entity.Booking;
import com.railway.reservation.entity.Train;
import com.railway.reservation.entity.User;
import com.railway.reservation.repository.BookingRepository;
import com.railway.reservation.repository.TrainRepository;
import com.railway.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private UserRepository userRepository;
    public String bookTicket(BookingRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){

            return "User Not Found";

        }
        User user = userOptional.get();
        Optional<Train> trainAvailable=trainRepository.findById(request.getTrainId());
        if(trainAvailable.isEmpty()){
            return "Train Not Found";
        }
        Train train=trainAvailable.get();
        if(train.getAvailableSeats()<=0){
            return "No seats available";
        }

        Booking booking=new Booking();
        booking.setPassengerName(request.getPassengerName());
        booking.setJourneyDate(request.getJourneyDate());
        booking.setTrain(train);
        booking.setUser(user);
        train.setAvailableSeats(train.getAvailableSeats()-1);
        trainRepository.save(train);
        bookingRepository.save(booking);
        return "Booking Successfull";
    }
    public String cancelTicket(Long bookingID){
        Optional<Booking> bookingAvailable=bookingRepository.findById(bookingID);
        if(bookingAvailable.isEmpty()){
            return "Booking Not Found";
        }
        Booking booking=bookingAvailable.get();
        Train train=booking.getTrain();
        train.setAvailableSeats(train.getAvailableSeats()+1);
        trainRepository.save(train);
        bookingRepository.delete(booking);
        return "Booking deleted successfully";
    }
    public List<BookingResponse> getBookings(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();
        User user=userRepository.findByEmail(email).get();
        List<Booking> bookings=bookingRepository.findByUser(user);
        List<BookingResponse> response=new ArrayList<>();
        for(Booking booking:bookings){
            BookingResponse dto=new BookingResponse();
            dto.setBookingId(booking.getId());
            dto.setPassengerName(booking.getPassengerName());
            dto.setJourneyDate(booking.getJourneyDate());
            dto.setTrainName(booking.getTrain().getTrainName());
            dto.setTrainName(booking.getTrain().getTrainName());
            response.add(dto);
        }
        return response;
    }
}
