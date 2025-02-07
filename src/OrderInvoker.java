// Invoker for Commands
import java.util.*;

class OrderInvoker {
    private Queue<Command> commandQueue = new LinkedList<>();
    
    public void takeCommand(Command command) {
        commandQueue.offer(command);
    }
    
    public void processCommands() {
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            command.execute();
        }
    }
}