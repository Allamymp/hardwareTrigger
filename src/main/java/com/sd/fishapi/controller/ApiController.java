package com.sd.fishapi.controller;


import com.sd.fishapi.dto.RequestDTO;
import com.sd.fishapi.dto.ResponseDTO;
import com.sd.fishapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService service;

    @GetMapping("/allEvents")
    public ResponseEntity<?> allEvents(){
        return ResponseEntity.ok().body(service.findAllEvent());
    }

    @PostMapping("/registerEvent")
    public ResponseEntity<?> registerEvent(@RequestBody RequestDTO data) {
        service.registerEvent(data.newDate(),data.hardwareId());
        return ResponseEntity.ok().body("New Event created to " + data.newDate());
    }

    @PostMapping("/removeEvent")
    public ResponseEntity<?> removeEvent(@RequestBody RequestDTO data) {
        service.removeEvent(data.newDate());
        return ResponseEntity.ok().body("Event in " + data.newDate() + " removed.");
    }

    @GetMapping("/allHardwares")
    public ResponseEntity<?> allHardwares(){
        return ResponseEntity.ok().body(service.findAllHardware());
    }

    @PostMapping("/registerHardware")
    public ResponseEntity<?> registerHardware(@RequestBody RequestDTO data) {
        service.registerHardware(data.model(), data.url());
        return  ResponseEntity.ok().body("new Hardware registered!");
    }
    @PostMapping("/removeHardware")
    public ResponseEntity<?> editHardware(@RequestBody RequestDTO data) {
        service.removeHardware(data.hardwareId());
        return  ResponseEntity.ok().body("new Hardware registered!");
    }

    @PostMapping("/trigger")
    public ResponseEntity<?> triggerToHardware(@RequestBody ResponseDTO data){
        service.triggerPostRequest(data.hardwareId());
        return ResponseEntity.ok().body("Sucessful Request!");
    }

}
