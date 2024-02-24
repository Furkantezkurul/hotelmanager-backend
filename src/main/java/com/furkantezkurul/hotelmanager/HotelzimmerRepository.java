package com.furkantezkurul.hotelmanager;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelzimmerRepository extends MongoRepository<Hotelzimmer, ObjectId> {
}
