package ma.enset.radarservice.query.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.queries.FindAllRadars;
import ma.enset.radarservice.query.entities.Radar;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/radars")
@AllArgsConstructor
public class RadarRestController {
    private QueryGateway queryGateway;

    @GetMapping("/allRadars")
    public List<Radar> getAllRadars(){
        return queryGateway.query(new FindAllRadars(), ResponseTypes.multipleInstancesOf(Radar.class)).join();
    }
}
