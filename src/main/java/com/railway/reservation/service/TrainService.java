package com.railway.reservation.service;

import com.railway.reservation.dto.TrainRequest;
import com.railway.reservation.dto.TrainResponse;
import com.railway.reservation.entity.Train;
import com.railway.reservation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;
    public String addTrain(TrainRequest newTrain){
        Optional<Train> existingTrain=trainRepository.findByTrainNumber(newTrain.getTrainNumber());

        if(existingTrain.isPresent()){
            return "Train already Present";
        }
        Train train=new Train();
        train.setTrainNumber(newTrain.getTrainNumber());
        train.setTrainName(newTrain.getTrainName());
        train.setDestination(newTrain.getDestination());
        train.setSource(newTrain.getSource());
        train.setTotalSeats(newTrain.getTotalSeats());
        train.setAvailableSeats(newTrain.getTotalSeats());
        trainRepository.save(train);
        return "Train added successfully";
    }
    public List<TrainResponse> searchTrains(String source, String destination){
        List<Train> trains=trainRepository.findBySourceAndDestination(source,destination);
        List<TrainResponse> response = new ArrayList<>();
        if(trains.isEmpty()) {
            return response;
        }
        for(Train train : trains){

            TrainResponse dto = new TrainResponse();
            dto.setId(train.getId());
            dto.setTrainNumber(train.getTrainNumber());
            dto.setTrainName(train.getTrainName());
            dto.setSource(train.getSource());
            dto.setDestination(train.getDestination());
            dto.setAvailableSeats(train.getAvailableSeats());
            response.add(dto);
        }
        return response;
    }
    public List<TrainResponse> getAllTrains(){
        List<Train> trains=trainRepository.findAll();
        List<TrainResponse> response=new ArrayList<>();
        for(Train train:trains){
            TrainResponse dto = new TrainResponse();
            dto.setId(train.getId());
            dto.setTrainNumber(train.getTrainNumber());
            dto.setTrainName(train.getTrainName());
            dto.setSource(train.getSource());
            dto.setDestination(train.getDestination());
            dto.setAvailableSeats(train.getAvailableSeats());
            response.add(dto);
        }
        return response;
    }
}
