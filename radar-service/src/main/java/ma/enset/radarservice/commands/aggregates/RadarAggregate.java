package ma.enset.radarservice.commands.aggregates;

import ma.enset.commonapi.commands.CreateRadarCommand;
import ma.enset.commonapi.events.RadarCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class RadarAggregate {
    @AggregateIdentifier
    private String radarId;
    private double maxVitesse;
    private double latitude;
    private double longitude;

    public RadarAggregate() {
        //Obligatoire par Axon
    }

    @CommandHandler
    public RadarAggregate(CreateRadarCommand command){
        if (command.getMaxVitesse() < 0) {
            throw new RuntimeException("Vitesse maximale ne doit pas etre negative");
        }
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getMaxVitesse(),
                command.getLatitude(),
                command.getLongitude()));
    }

    @EventSourcingHandler //émutter l'état de l'aggregate
    public void on(RadarCreatedEvent event){
        this.radarId = event.getId();
        this.maxVitesse = event.getMaxVitesse();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
    }
}
