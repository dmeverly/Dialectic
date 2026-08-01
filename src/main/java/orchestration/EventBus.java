package orchestration;

import models.Model;
import util.Logger;

import java.util.ArrayList;

public class EventBus {
    private final ArrayList<Model> models;
    private int finalSize;
    private boolean closed = false;

    public EventBus() {
        this.models = new ArrayList<>();
    }

    public void registerModel(Model model) {
        if (closed) {
            throw new IllegalStateException("Cannot register model after EventBus is closed.");
        }
        model.setID(this.models.size());
        this.models.add(model);
    }

    public void close() {
        this.finalSize = this.models.size();
        this.closed = true;
    }

    public void emit(Event event) {
        if (!closed) {
            throw new IllegalStateException("Cannot emit events before EventBus is closed.");
        }
        if(event.getModelID() >= this.finalSize) {
            Logger.log(event.getInput());
            return;
        }
        for (Model model : models) {
            model.onEvent(event);
        }
    }

    public void emit(String input) {
        Logger.log("Emitting input: " + input + " to model ID 0");
        if (!closed) {
            throw new IllegalStateException("Cannot emit events before EventBus is closed.");
        }
        Event event = new Event(Event.EventType.PROCESSING_STEP, 0, input);
        emit(event);
    }
}
