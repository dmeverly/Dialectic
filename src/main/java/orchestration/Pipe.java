package orchestration;

import java.util.ArrayList;

public class Pipe {
    private final ArrayList<String> buffer;

    public Pipe() {
        this.buffer = new ArrayList<>();
    }

    public void write(String data) {
        this.buffer.add(data);
    }

    public String read() {
        if (!this.buffer.isEmpty()) {
            return this.buffer.remove(0);
        }
        return "";
    }
}