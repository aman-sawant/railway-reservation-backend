package com.railway.reservation.service;

import com.railway.reservation.dto.BookingRequest;
import com.railway.reservation.entity.Booking;
import com.railway.reservation.entity.Train;
import com.railway.reservation.repository.BookingRepository;
import com.railway.reservation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TrainRepository trainRepository;
    public String bookTicket(BookingRequest request){
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
}
