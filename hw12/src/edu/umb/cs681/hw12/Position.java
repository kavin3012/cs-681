package edu.umb.cs681.hw12;

import java.util.List;

public record Position(double laititude, double longitude, double altitude) {
    
    public List<Double> coordinate() {
        return List.of(this.laititude(), this.longitude(), this.altitude());
    }

    public Position change(double laititude, double longitude, double altitude) {
        return new Position(laititude, longitude, altitude);
    }

    public boolean higherAltThan(Position position) {
        return this.altitude() > position.altitude();
    }

    public boolean lowerAltThan(Position position) {
        return this.altitude() < position.altitude();
    }

    public boolean northOf(Position position) {
        return this.laititude() > position.laititude();
    }

    public boolean southOf(Position position) {
        return this.laititude() < position.laititude();
    }

}
