import java.io.File;

import models.*;
import orchestration.Pipeline;
import util.Logger;

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

        String topic = "test input. you say output";

        LocalModel initial = new LocalModel(initialModelName, provider, model, "Answer the question", port);
        LocalModel aye = new LocalModel(ayeModelName, provider, model, "Present logical argument in favor of the response or say \"defer\" if a stronger argument cannot be made", port);
        LocalModel nay = new LocalModel(nayModelName, provider, model, "Present logical argument against the response or say \"defer\" if a stronger argument cannot be made", port);
        LocalModel judge = new LocalModel(judgeModelName, provider, model, "Evaluate the arguments. Output a summary of the strongest points of the discussion.", port);
        LocalModel output = new LocalModel("Output", provider, model, "Output the final answer to the question considering the arguments.", port);

        Pipeline pipeline = new Pipeline();
        pipeline.addModel(initial);
        pipeline.addModel(aye);
        pipeline.addModel(nay);
        pipeline.addModel(judge);
        pipeline.addModel(output);
        pipeline.sealPipeline();
        pipeline.load(topic);
        pipeline.process();

        
    }
}
