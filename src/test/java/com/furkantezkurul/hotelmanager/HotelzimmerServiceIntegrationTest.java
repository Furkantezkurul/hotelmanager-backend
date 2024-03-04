package com.furkantezkurul.hotelmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@DataMongoTest
@Import(HotelzimmerService.class)
public class HotelzimmerServiceIntegrationTest {

    @Autowired
    private HotelzimmerService hotelzimmerService;

    @Autowired
    private HotelzimmerRepository hotelzimmerRepository;

    @BeforeEach
    void setUp() {
        hotelzimmerRepository.deleteAll();
    }

    @Test
    void addAndRetrieveHotelzimmerTest() {
        Hotelzimmer newHotelzimmer = new Hotelzimmer(null, "105", "Suite", false, false);
        ResponseEntity<?> responseEntity = hotelzimmerService.addHotelzimmer(newHotelzimmer);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

        Optional<Hotelzimmer> retrievedHotelzimmer = hotelzimmerRepository.findHotelzimmerByZimmerNummer("105");
        assertThat(retrievedHotelzimmer).isPresent().hasValueSatisfying(hotelzimmer -> {
            assertThat(hotelzimmer.getZimmerGroesse()).isEqualTo("Suite");
            assertThat(hotelzimmer.isMinibar()).isFalse();
            assertThat(hotelzimmer.isBesetzt()).isFalse();
        });
    }

    @Test
    void updateHotelzimmerTest() {
        // Add a room first
        Hotelzimmer existingHotelzimmer = new Hotelzimmer(null, "106", "Doppelzimmer", true, false);
        hotelzimmerRepository.save(existingHotelzimmer);

        // Update the room
        Hotelzimmer updatedHotelzimmer = new Hotelzimmer(null, "106", "Suite", false, true);
        ResponseEntity<?> responseEntity = hotelzimmerService.updateHotelzimmer("106", updatedHotelzimmer);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

        Optional<Hotelzimmer> retrievedHotelzimmer = hotelzimmerRepository.findHotelzimmerByZimmerNummer("106");
        assertThat(retrievedHotelzimmer).isPresent().hasValueSatisfying(hotelzimmer -> {
            assertThat(hotelzimmer.getZimmerGroesse()).isEqualTo("Suite");
            assertThat(hotelzimmer.isMinibar()).isFalse();
            assertThat(hotelzimmer.isBesetzt()).isTrue();
        });
    }

    @Test
    void deleteHotelzimmerTest() {
        // Add a room first
        Hotelzimmer hotelzimmerToDelete = new Hotelzimmer(null, "107", "Einzelzimmer", false, false);
        hotelzimmerRepository.save(hotelzimmerToDelete);

        // Delete the room
        boolean result = hotelzimmerService.deleteHotelzimmer("107");
        assertThat(result).isTrue();

        Optional<Hotelzimmer> retrievedHotelzimmer = hotelzimmerRepository.findHotelzimmerByZimmerNummer("107");
        assertThat(retrievedHotelzimmer).isNotPresent();
    }

    @Test
    void retrieveAllHotelzimmerTest() {
        // Add multiple rooms
        hotelzimmerRepository.save(new Hotelzimmer(null, "108", "Einzelzimmer", true, false));
        hotelzimmerRepository.save(new Hotelzimmer(null, "109", "Doppelzimmer", false, true));

        List<Hotelzimmer> hotelzimmerList = hotelzimmerService.allHotelzimmer();
        assertThat(hotelzimmerList).hasSize(2);
    }
}
