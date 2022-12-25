package ma.enset.radarservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateRadarCommand;
import ma.enset.commonapi.dtos.CreateRadarRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/radar")
@AllArgsConstructor
public class RadarCommandController {
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createRadar(@RequestBody CreateRadarRequestDTO createRadarRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new CreateRadarCommand(
                UUID.randomUUID().toString(),
                createRadarRequestDTO.getMaxVitesse(),
                createRadarRequestDTO.getLatitude(),
                createRadarRequestDTO.getLongitude()
        ));
        return response;
    }

}