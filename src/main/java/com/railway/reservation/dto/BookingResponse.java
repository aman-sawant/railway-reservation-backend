package com.railway.reservation.dto;

import java.time.LocalDate;

public class BookingResponse {
    private Long bookingId;
    private String passengerName;
    private LocalDate journeyDate;
    private String trainName;
    private String trainNumber;

    public BookingResponse() {
    }

    public BookingResponse(Long bookingId, String passengerName, LocalDate journeyDate, String trainName, String trainNumber) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }

    public Long getBookingId() {

        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
}
