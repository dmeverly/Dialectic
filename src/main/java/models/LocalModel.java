package models;

import types.SynapSysMessage;
import util.Logger;

public class LocalModel extends Model{
    private final int port;

    public LocalModel(String name, String provider, String model, String goal, int port) {
        super(name, provider, model, goal);
        this.port = port;
    }

    @Override
    public SynapSysMessage query(String input) {
        this.context.add(input);

        String prompt = String.join("\n", this.context) + "\n" + this.goal;
        Logger.log("---------------------\n"+"[" + this.name + "] prompt: " + prompt);
        Logger.log("---------------------\n");
        SynapSysMessage request = new SynapSysMessage(this.provider, this.model, prompt);

        SynapSysMessage reply = generator.generate(request, this.port);

        this.context.add("[" + this.name + "] " + reply.getMessage());  
        Logger.log("---------------------\n"+"[" + this.name + "] reply: " + reply.getMessage());
        Logger.log("---------------------\n");
        return reply;
    }
}
