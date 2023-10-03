package pt.pa.command;


import java.util.Stack;

public class CommandManager {
    private Stack<Command> commands;

    public CommandManager() {
        commands = new Stack();
    }

    public void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

}