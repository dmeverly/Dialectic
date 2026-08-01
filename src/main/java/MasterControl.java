import java.io.File;

import models.*;
import util.Logger;
import orchestration.*;

public class MasterControl {
    public static void main(String[] args) {
        File logFile = new File("debate_log.txt");
        Logger.setLogFile(logFile);

        String initialModelName = "Initial";
        String ayeModelName = "Aye";
        String nayModelName = "Nay";
        String judgeModelName = "Judge";
        String provider = "ollama-local";
        String model = "qwen3:4b";
        int port = 11434;

        String topic = "what came first, the chicken or the egg?";
        EventBus eventBus = new EventBus();

        LocalModel initial = new LocalModel(eventBus, initialModelName, provider, model, "Answer the question", port);
        LocalModel aye = new LocalModel(eventBus, ayeModelName, provider, model,
                "Now, Present logical argument in favor of the response",
                port);
        LocalModel nay = new LocalModel(eventBus, nayModelName, provider, model,
                "Now, Present logical argument against the response",
                port);
        LocalModel judge = new LocalModel(eventBus, judgeModelName, provider, model,
                "Now, Evaluate the arguments and make a logical determination on the most correct response.", port);
        LocalModel output = new LocalModel(eventBus, "Output", provider, model,
                "Summarize the key points of the discussion.", port);

        eventBus.registerModel(initial);
        eventBus.registerModel(aye);
        eventBus.registerModel(nay);
        eventBus.registerModel(judge);
        eventBus.registerModel(output);
        eventBus.close();
        eventBus.emit("Question: " + topic);
    }
}
