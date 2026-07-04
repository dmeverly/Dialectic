package orchestration;

import util.Logger;

public class OutputPipe extends Pipe {
    @Override
    public void write(String data) {
        Logger.log(data);
    }
}
