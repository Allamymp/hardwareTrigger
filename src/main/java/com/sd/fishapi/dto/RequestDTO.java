package com.sd.fishapi.dto;

import java.time.LocalDateTime;

public record RequestDTO(

          String model
        , String url
        , String hardwareId
        , LocalDateTime newDate
        , LocalDateTime actualDate) {
}
