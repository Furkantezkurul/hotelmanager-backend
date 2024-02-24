package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Hotelzimmer> singleHotelzimmer(ObjectId id){
        return hotelzimmerRepository.findById(id);
    }
}
