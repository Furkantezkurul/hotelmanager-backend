package com.furkantezkurul.hotelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;

    @Autowired
    private HotelzimmerService hotelzimmerService;

    @Override
    public void run(String... args) throws Exception {
        Hotelzimmer hotelzimmer1 = new Hotelzimmer(null, "101", "Einzelzimmer", true, false);
        Hotelzimmer hotelzimmer2 = new Hotelzimmer(null, "102", "Doppelzimmer", true, false);
        Hotelzimmer hotelzimmer3 = new Hotelzimmer(null, "103", "Suite", false, false);
        hotelzimmerService.addHotelzimmer(hotelzimmer1);
        hotelzimmerService.addHotelzimmer(hotelzimmer2);
        hotelzimmerService.addHotelzimmer(hotelzimmer3);
    }
}
