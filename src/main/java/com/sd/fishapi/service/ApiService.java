package com.sd.fishapi.service;


import com.sd.fishapi.model.entities.Event;
import com.sd.fishapi.model.hardwares.Hardware;
import com.sd.fishapi.repository.EventRepository;
import com.sd.fishapi.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    HardwareRepository hardwareRepository;

    public void registerEvent(LocalDateTime date, String hardwareId) {
        Optional<Hardware> hardware = hardwareRepository.findById(hardwareId);
        eventRepository.save(new Event(date, hardware.get()));
    }

    public void removeEvent(LocalDateTime date) {
        eventRepository.delete(eventRepository.findByDate(date));
    }

    public List<Event> findAllEvent() {
        return eventRepository.findAll();
    }

    public void registerHardware(String model, String url) {
        hardwareRepository.save(new Hardware(model, url));
    }

    public void removeHardware(String id) {
        hardwareRepository.findById(id).ifPresent(hardwareRepository::delete);
    }

    public List<Hardware> findAllHardware() {
        return hardwareRepository.findAll();
    }

    public void triggerPostRequest(String id) {
        Optional<Hardware> hardware = hardwareRepository.findById(id);

        if (hardware.isPresent()) {
            String hardwareUrl = hardware.get().getUrl();

            if (hardwareUrl != null && !hardwareUrl.isEmpty()) {
                // Adiciona o esquema "http" à URL, se não estiver presente
                if (!hardwareUrl.startsWith("http://") && !hardwareUrl.startsWith("https://")) {
                    hardwareUrl = "http://" + hardwareUrl;
                }

                // Garante que a URL termine com uma barra
                if (!hardwareUrl.endsWith("/")) {
                    hardwareUrl += "/";
                }

                String url = hardwareUrl.concat("abrir");

                // Construa o corpo da solicitação no formato JSON
                String jsonBody = "{\"value\":\"True\"}";

                HttpClient client = HttpClient.newHttpClient();

                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                            .uri(URI.create(url))
                            .header("Content-Type", "application/json")
                            .build();

                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .thenAccept(System.out::println)
                            .join();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Trate exceções, se necessário
                }
            } else {
                // Trate o caso em que a URL do hardware está vazia
            }
        } else {
            // Trate o caso em que o hardware não foi encontrado
        }
    }
}
