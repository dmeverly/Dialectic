package orchestration;

import java.util.ArrayList;

import models.Model;
import util.Logger;

public class Pipeline {
    private final ArrayList<Filter> filters;
    private Pipe previousPipe;
    private final Pipe inputPipe;

    public Pipeline() {
        this.filters = new ArrayList<>();
        this.previousPipe = new Pipe();
        this.inputPipe = previousPipe;
    }

    public void addModel(Model model) {
        Pipe nextPipe = new Pipe();
        filters.add(new Filter(previousPipe, nextPipe, model));
        previousPipe = nextPipe;
    }

    public void sealPipeline() {
        if (!filters.isEmpty()) {
            Filter lastFilter = filters.get(filters.size() - 1);
            lastFilter = new Filter(lastFilter.getInputPipe(), new OutputPipe(), lastFilter.getModel());
            filters.remove(filters.size()-1);
            filters.add(lastFilter);
        }
    }

    public void process(){
        for (Filter filter : filters) {
            Logger.log("Processing filter for model: " + filter.getModel().getName());
            filter.process();
        }
    }

    public void load(String input) {
        this.inputPipe.write(input);
    }
}
