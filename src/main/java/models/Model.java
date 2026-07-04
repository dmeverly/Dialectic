package models;

import java.util.ArrayList;

import api.Generator;
import types.SynapSysMessage;
import util.Logger;

public class Model {
    protected final String name;
    protected final String provider;
    protected final String model;
    protected final ArrayList<String> context;
    protected final String goal;
    protected final Generator generator;
    

    public Model(String name, String provider, String model, String goal) {
        this.name = name;
        this.provider = provider;
        this.model = model;
        this.context = new ArrayList<>();
        this.goal = goal;
        this.context.add("Your name: " + this.name);
        this.context.add("Your goal: " + this.goal);
        this.generator = new Generator();
    }

    public SynapSysMessage query(String input) {
        this.context.add(input);

        String prompt = String.join("\n", this.context) + "\n" + this.goal;
        Logger.log("---------------------\n"+"[" + this.name + "] prompt: " + prompt);
        Logger.log("---------------------\n");
        SynapSysMessage request = new SynapSysMessage(this.provider, this.model, prompt);

        SynapSysMessage reply = generator.generate(request);

        this.context.add("[" + this.name + "] " + reply.getMessage());  
        Logger.log("---------------------\n"+"[" + this.name + "] reply: " + reply.getMessage());
        Logger.log("---------------------\n");
        return reply;
    }

    public String getName() {
        return name;
    }
}
