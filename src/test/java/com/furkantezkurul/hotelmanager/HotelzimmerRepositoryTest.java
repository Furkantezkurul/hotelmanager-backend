package com.furkantezkurul.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class HotelzimmerRepositoryTest {

    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;

    @BeforeEach
    void setup() {
        hotelzimmerRepository.deleteAll();
    }

    @Test
    public void findByZimmerNummerTest() {
        // Initialisiere einige Testdaten
        Hotelzimmer h1 = new Hotelzimmer(null, "101", "Einzelzimmer", true, false);
        Hotelzimmer h2 = new Hotelzimmer(null, "102", "Doppelzimmer", true, false);
        Hotelzimmer h3 = new Hotelzimmer(null, "103", "Suite", false, false);

        hotelzimmerRepository.save(h1);
        hotelzimmerRepository.save(h2);
        hotelzimmerRepository.save(h3);
        Optional<Hotelzimmer> found = hotelzimmerRepository.findHotelzimmerByZimmerNummer("101");
        assertThat(found.isPresent()).isTrue();
        found.ifPresent(hotelzimmer -> assertThat(hotelzimmer.getZimmerGroesse()).isEqualTo("Einzelzimmer"));
        found.ifPresent(hotelzimmer -> assertThat(hotelzimmer.getZimmerNummer()).isEqualTo("101"));
        found.ifPresent(hotelzimmer -> assertThat(hotelzimmer.isBesetzt()).isFalse());
        found.ifPresent(hotelzimmer -> assertThat(hotelzimmer.isMinibar()).isTrue());

    }

    @Test
    public void FindAllTest() {
        // Initialisiere einige Testdaten
        Hotelzimmer h1 = new Hotelzimmer(null, "101", "Einzelzimmer", true, false);
        Hotelzimmer h2 = new Hotelzimmer(null, "102", "Doppelzimmer", true, false);
        Hotelzimmer h3 = new Hotelzimmer(null, "103", "Suite", false, false);

        hotelzimmerRepository.save(h1);
        hotelzimmerRepository.save(h2);
        hotelzimmerRepository.save(h3);
        List<Hotelzimmer> hotelzimmerList = hotelzimmerRepository.findAll();
        assertThat(hotelzimmerList).hasSize(3);
    }


}

