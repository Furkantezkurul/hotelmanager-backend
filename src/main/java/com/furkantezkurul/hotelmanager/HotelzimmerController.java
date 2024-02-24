package com.furkantezkurul.hotelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotelzimmer")
public class HotelzimmerController {

    @Autowired
    private HotelzimmerService hotelzimmerService;
    @GetMapping
    public ResponseEntity<List<Hotelzimmer>> getAllHotelzimmer(){
      return new ResponseEntity<List<Hotelzimmer>>(hotelzimmerService.allHotelzimmer(), HttpStatus.OK);
    }
}
