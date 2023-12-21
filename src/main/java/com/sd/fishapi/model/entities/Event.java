package com.sd.fishapi.model.entities;


import com.sd.fishapi.model.hardwares.Hardware;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name="hardware_id")
    private Hardware hardware;


    public Event(LocalDateTime date, Hardware hardware){
        this.date = date;
        this.hardware = hardware;
    }
    public Instant returnDateToInstant(Date date){
        return date.toInstant();
    }
    public Date returnInstantToDate(Instant instant){
        return Date.from(instant);
    }

}
