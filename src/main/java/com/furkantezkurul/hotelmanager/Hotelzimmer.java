package com.furkantezkurul.hotelmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotelzimmer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotelzimmer {
    @Id
    private ObjectId id;
    private String zimmerNummer;
    private String zimmerGroesse;
    private boolean minibar;
    private boolean besetzt;

}
