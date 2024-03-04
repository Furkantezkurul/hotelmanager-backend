package com.furkantezkurul.hotelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;

    @Override
    public void run(String... args) throws Exception {
        createHotelzimmerIfNotFound("101", "Einzelzimmer", true, false);
        createHotelzimmerIfNotFound("102", "Doppelzimmer", true, false);
        createHotelzimmerIfNotFound("103", "Suite", false, false);
    }

    private void createHotelzimmerIfNotFound(String zimmerNummer, String zimmerGroesse, boolean minibar, boolean besetzt) {
        Optional<Hotelzimmer> hotelzimmerOptional = hotelzimmerRepository.findHotelzimmerByZimmerNummer(zimmerNummer);
        if (!hotelzimmerOptional.isPresent()) {
            Hotelzimmer newHotelzimmer = new Hotelzimmer(null, zimmerNummer, zimmerGroesse, minibar, besetzt);
            hotelzimmerRepository.save(newHotelzimmer);
            System.out.println("Hotelzimmer mit Zimmernummer " + zimmerNummer + " wurde erstellt.");
        } else {
            System.out.println("Hotelzimmer mit Zimmernummer " + zimmerNummer + " existiert bereits.");
        }
    }
}
