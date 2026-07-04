package orchestration;

import models.Model;
import util.Logger;

public class Filter {
    private final Pipe inputPipe;
    private final Pipe outputPipe;
    private final Model model;

    public Filter(Pipe inputPipe, Pipe outputPipe, Model model) {
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.model = model;
    }

    public void process() {
        String response = this.model.query(this.inputPipe.read()).getMessage();
        Logger.log("[" + this.model.getName() + "] response: " + response);
        this.outputPipe.write(response);
    }

    public Pipe getInputPipe() {
        return inputPipe;
    }

    public Pipe getOutputPipe() {
        return outputPipe;
    }

    public Model getModel() {
        return model;
    }
}
