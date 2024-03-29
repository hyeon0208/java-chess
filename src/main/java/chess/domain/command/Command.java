package chess.domain.command;

import java.util.List;

public class Command {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final List<String> commands;

    public Command(final List<String> commands) {
        this.commands = commands;
    }

    public CommandType getCommand() {
        return CommandType.from(commands);
    }

    public String getSource() {
        return commands.get(SOURCE_INDEX);
    }

    public String getTarget() {
        return commands.get(TARGET_INDEX);
    }
}
