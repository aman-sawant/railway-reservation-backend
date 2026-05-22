package com.railway.reservation.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passengerName;
    private LocalDate journeyDate;
    @ManyToOne
    private Train train;

    public Booking() {
    }

    public Booking(Long id, String passengerName, LocalDate journeyDate, Train train) {
        this.id = id;
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;
        this.train = train;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
