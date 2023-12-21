package com.sd.fishapi.model.hardwares;


import com.sd.fishapi.model.entities.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String url;
    @OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL)
    private List<Event> events;

    public Hardware(  String model, String url){
        this.model = model;
        this.url = url;
    }


}
