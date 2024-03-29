package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum CommandType {
    START("start", 1),
    MOVE("move", 3),
    END("end", 1),
    STATUS("status", 1),
    ;

    private static final int COMMAND_INDEX = 0;

    private final String value;
    private final int size;

    CommandType(final String value, final int size) {
        this.value = value;
        this.size = size;
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
}
