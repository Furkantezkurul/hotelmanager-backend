package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{zimmerNummer}")
    public ResponseEntity<Optional<Hotelzimmer>> getSingleHotelzimmer(@PathVariable String zimmerNummer){
        return new ResponseEntity<Optional<Hotelzimmer>>(hotelzimmerService.singleHotelzimmer(zimmerNummer), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addHotelzimmer(@RequestBody Hotelzimmer newHotelzimmer) {
        return hotelzimmerService.addHotelzimmer(newHotelzimmer);
    }

    @PutMapping("/{zimmerNummer}")
    public ResponseEntity<?> updateHotelzimmer(@PathVariable String zimmerNummer, @RequestBody Hotelzimmer updatedHotelzimmer) {
        return hotelzimmerService.updateHotelzimmer(zimmerNummer, updatedHotelzimmer);
    }

    @DeleteMapping("/{zimmerNummer}")
    public ResponseEntity<?> deleteHotelzimmer(@PathVariable String zimmerNummer) {
        boolean deleted = hotelzimmerService.deleteHotelzimmer(zimmerNummer);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
