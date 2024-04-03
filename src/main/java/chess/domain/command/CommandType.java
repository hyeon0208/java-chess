package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum CommandType {
    START("start", 1, new StartCommand()),
    MOVE("move", 3, new MoveCommand()),
    END("end", 1, new EndCommand()),
    STATUS("status", 1, new StatusCommand()),
    SEARCH("search", 1, new SearchCommand()),
    LOAD("load", 2, new LoadCommand()),
    SAVE("save", 1, new SaveCommand()),
    ;

    private static final int COMMAND_INDEX = 0;

    private final String value;
    private final int size;
    private final CommandAction action;

    CommandType(final String value, final int size, final CommandAction action) {
        this.value = value;
        this.size = size;
        this.action = action;
    }

    public static CommandType from(final List<String> command) {
        if (command == null) {
            throw new IllegalArgumentException("명령어를 입력해주세요.");
        }

        return Arrays.stream(CommandType.values())
                .filter(type -> type.value.equals(command.get(COMMAND_INDEX)))
                .filter(type -> type.size == command.size())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 커맨드입니다."));
    }

    public CommandAction action() {
        return action;
    }
}
