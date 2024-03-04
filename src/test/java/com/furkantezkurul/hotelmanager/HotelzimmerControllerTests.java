package com.furkantezkurul.hotelmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(HotelzimmerController.class)
public class HotelzimmerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelzimmerService hotelzimmerService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllHotelzimmerTest() throws Exception {
        Hotelzimmer hotelzimmer1 = new Hotelzimmer(null, "101", "Einzelzimmer", true, false);
        Hotelzimmer hotelzimmer2 = new Hotelzimmer(null, "102", "Doppelzimmer", true, false);
        List<Hotelzimmer> allHotelzimmer = Arrays.asList(hotelzimmer1, hotelzimmer2);

        given(hotelzimmerService.allHotelzimmer()).willReturn(allHotelzimmer);

        mockMvc.perform(get("/api/v1/hotelzimmer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(allHotelzimmer.size()));
    }

    @Test
    public void getSingleHotelzimmerTest() throws Exception {
        Hotelzimmer hotelzimmer = new Hotelzimmer(null, "101", "Einzelzimmer", true, false);

        given(hotelzimmerService.singleHotelzimmer("101")).willReturn(Optional.of(hotelzimmer));

        mockMvc.perform(get("/api/v1/hotelzimmer/101")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.zimmerNummer").value(hotelzimmer.getZimmerNummer()));
    }
}

