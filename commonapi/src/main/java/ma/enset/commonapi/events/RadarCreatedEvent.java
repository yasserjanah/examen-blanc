package ma.enset.commonapi.events;

import lombok.Getter;

public class RadarCreatedEvent extends BaseEvent<String> {
    @Getter private double maxVitesse;
    @Getter private double latitude;
    @Getter private double longitude;

    public RadarCreatedEvent(String id, double maxVitesse, double latitude, double longitude) {
        super(id);
        this.maxVitesse = maxVitesse;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
