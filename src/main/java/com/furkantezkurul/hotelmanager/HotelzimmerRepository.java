package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelzimmerRepository extends MongoRepository<Hotelzimmer, ObjectId> {

    Optional<Hotelzimmer> findHotelzimmerByZimmerNummer(String zimmerNummer);
}
