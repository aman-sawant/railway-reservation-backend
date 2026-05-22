package com.railway.reservation.dto;

import java.time.LocalDate;

public class BookingRequest {
    private String passengerName;
    private LocalDate journeyDate;
    private Long trainId;

    public BookingRequest() {
    }

    public BookingRequest(String passengerName, LocalDate journeyDate, Long trainId) {
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;
        this.trainId = trainId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassesngerName(String passesngerName) {
        this.passengerName = passesngerName;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }
}
