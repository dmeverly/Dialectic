package orchestration;

public class Event {
    public enum EventType {
        PROCESSING_STEP
    }

    private final int modelID;
    private final EventType eventType;
    private final String input;

    public Event(EventType eventType, int modelID, String input) {
        this.modelID = modelID;
        this.eventType = eventType;
        this.input = input;
    }

    public int getModelID() {
        return this.modelID;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public String getInput() {
        return this.input;
    }
}