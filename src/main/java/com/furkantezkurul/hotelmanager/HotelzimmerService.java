package com.furkantezkurul.hotelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelzimmerService {
    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;
    public List<Hotelzimmer> allHotelzimmer(){
        System.out.println(hotelzimmerRepository.findAll());
        return hotelzimmerRepository.findAll();
    }
}
