package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Service
public class HotelzimmerService {
    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;
    public List<Hotelzimmer> allHotelzimmer(){
        System.out.println(hotelzimmerRepository.findAll());
        return hotelzimmerRepository.findAll();
    }

    public Optional<Hotelzimmer> singleHotelzimmer(String zimmerNummer){
        return hotelzimmerRepository.findHotelzimmerByZimmerNummer(zimmerNummer);
    }

    public ResponseEntity<?> addHotelzimmer(Hotelzimmer newHotelzimmer) {
        // Validate the zimmerNummer
        if (!newHotelzimmer.getZimmerNummer().matches("\\d+")) {
            return ResponseEntity.badRequest().body("Zimmernummer must be a number");
        }

        // Check if zimmerNummer is already used
        Optional<Hotelzimmer> existingHotelzimmer = hotelzimmerRepository.findHotelzimmerByZimmerNummer(newHotelzimmer.getZimmerNummer());
        if (existingHotelzimmer.isPresent()) {
            return ResponseEntity.badRequest().body("Zimmernummer is already used");
        }

        // Save the new hotel room
        Hotelzimmer savedHotelzimmer = hotelzimmerRepository.save(newHotelzimmer);
        return ResponseEntity.ok(savedHotelzimmer);
    }
}
