package com.railway.reservation.controller;

import com.railway.reservation.dto.TrainRequest;
import com.railway.reservation.dto.TrainResponse;
import com.railway.reservation.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
@Validated
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping("/addtrain")
    public ResponseEntity<String> addNewTrain(@Valid @RequestBody TrainRequest request){
        String response=trainService.addTrain(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getalltrains")
    public ResponseEntity<List<TrainResponse>> getAllTrains(){
        List<TrainResponse> trains=trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }
    @GetMapping("/searchtrain")
    public ResponseEntity<List<TrainResponse>> getTrain(@RequestParam String source,@RequestParam String destination){
        List<TrainResponse> trains=trainService.searchTrains(source,destination);
        return ResponseEntity.ok(trains);
    }
}
