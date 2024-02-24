package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotelzimmer")
public class HotelzimmerController {

    @Autowired
    private HotelzimmerService hotelzimmerService;
    @GetMapping
    public ResponseEntity<List<Hotelzimmer>> getAllHotelzimmer(){
      return new ResponseEntity<List<Hotelzimmer>>(hotelzimmerService.allHotelzimmer(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hotelzimmer>> getSingleHotelzimmer(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Hotelzimmer>>(hotelzimmerService.singleHotelzimmer(id), HttpStatus.OK);
    }
}
