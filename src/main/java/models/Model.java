package models;

import java.util.ArrayList;

import api.Generator;
import orchestration.Event;
import types.SynapSysMessage;
import util.Logger;
import orchestration.*;

public class Model {
    protected final String name;
    protected final String provider;
    protected final String model;
    protected final ArrayList<String> context;
    protected final String goal;
    protected final Generator generator;
    protected int ID;
    protected EventBus eventBus;

    public Model(EventBus eventBus, String name, String provider, String model, String goal) {
        this.name = name;
        this.provider = provider;
        this.model = model;
        this.context = new ArrayList<>();
        this.goal = "Goal: " + goal;
        this.context.add("Your name: " + this.name);
        this.context.add("Your goal: " + this.goal);
        this.generator = new Generator();
        this.eventBus = eventBus;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getID() {
        return this.ID;
    }
    public SynapSysMessage query(String input) {
        this.context.add(input);

        String prompt = String.join("\n", this.context) + "\n" + this.goal;
        Logger.log("---------------------\n");
        SynapSysMessage request = new SynapSysMessage(this.provider, this.model, prompt);

        SynapSysMessage reply = generator.generate(request);

        this.context.add("[" + this.name + "] " + reply.getMessage());
        Logger.log("---------------------\n" + "[" + this.name + "] reply: " + reply.getMessage());
        Logger.log("\n---------------------\n");
        return reply;
    }

    public String getName() {
        return name;
    }

    public void onEvent(Event event) {
        if (event.getModelID() == this.ID && event.getEventType() == Event.EventType.PROCESSING_STEP) {
            this.eventBus.emit(new Event(Event.EventType.PROCESSING_STEP, event.getModelID()+1, this.query(event.getInput()).getMessage()));
        }
    }
}