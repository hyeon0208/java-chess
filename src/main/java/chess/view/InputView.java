package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String MOVE_COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readGameCommand() {
        return Arrays.stream(scanner.nextLine().split(MOVE_COMMAND_DELIMITER))
                .collect(Collectors.toList());
    }
}
