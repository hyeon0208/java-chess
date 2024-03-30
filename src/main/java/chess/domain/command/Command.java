package chess.domain.command;

import java.util.List;

public class Command {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    public static final int ID_INDEX = 1;

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

    public Long getLoadId() {
        return Long.valueOf(commands.get(ID_INDEX));
    }
}
