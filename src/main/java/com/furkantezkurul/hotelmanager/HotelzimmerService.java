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

    // Adds a new Hotelzimmer to the Repository, if the Zimmernummer is valid and doesn't already exist
    public ResponseEntity<?> addHotelzimmer(Hotelzimmer newHotelzimmer) {
        // Check if Zimmernummer is number
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

    /**
     * Updates the attributes of the Hotelzimmer with zimmerNummer
     *
     * @param zimmerNummer The number of the hotel room that should be edited
     * @param updatedHotelzimmer Hotelzimmer with the new atttributes
     */
    public ResponseEntity<?> updateHotelzimmer(String zimmerNummer, Hotelzimmer updatedHotelzimmer) {
        Optional<Hotelzimmer> hotelzimmerOptional = hotelzimmerRepository.findHotelzimmerByZimmerNummer(zimmerNummer);
        if (hotelzimmerOptional.isPresent()) {
            Hotelzimmer existingHotelzimmer = hotelzimmerOptional.get();
            // Update the attributes
            existingHotelzimmer.setZimmerGroesse(updatedHotelzimmer.getZimmerGroesse());
            existingHotelzimmer.setMinibar(updatedHotelzimmer.isMinibar());
            existingHotelzimmer.setBesetzt(updatedHotelzimmer.isBesetzt());
            // Save the updated hotel room
            hotelzimmerRepository.save(existingHotelzimmer);
            return ResponseEntity.ok(existingHotelzimmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletes the Hotelzimmer from Repository with the corresponding zimmerNummer
    public boolean deleteHotelzimmer(String zimmerNummer) {
        Optional<Hotelzimmer> hotelzimmerOptional = hotelzimmerRepository.findHotelzimmerByZimmerNummer(zimmerNummer);
        if (hotelzimmerOptional.isPresent()) {
            hotelzimmerRepository.delete(hotelzimmerOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
