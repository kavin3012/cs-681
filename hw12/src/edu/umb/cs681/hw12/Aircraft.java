package edu.umb.cs681.hw12;
import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {

    private AtomicReference<Position> position = new AtomicReference<>();

    public Aircraft(Position position) {
        this.position.set(position);
    }

    public Position getPosition() {
        return this.position.get();
    }

    public void setPosition(Position position) {
        this.position.set(position);
    }
    
}
