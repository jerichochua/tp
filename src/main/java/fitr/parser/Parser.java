package fitr.parser;

import fitr.command.AddExerciseCommand;
import fitr.command.AddFoodCommand;
import fitr.command.Command;
import fitr.command.DeleteCommand;
import fitr.command.ExitCommand;
import fitr.command.HelpCommand;
import fitr.command.InvalidCommand;
import fitr.command.ViewCommand;
import fitr.command.EditProfileCommand;
import fitr.common.Commands;
import fitr.exception.IllegalCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    public static Command parse(String userInput) throws IllegalCommandException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new IllegalCommandException();
        }

        String command = matcher.group("command").toLowerCase();
        String arguments = matcher.group("arguments").trim();

        switch (command) {
        case Commands.COMMAND_FOOD:
            return new AddFoodCommand(arguments);
        case Commands.COMMAND_EXERCISE:
            return new AddExerciseCommand(arguments);
        case Commands.COMMAND_VIEW:
            return new ViewCommand(arguments);
        case Commands.COMMAND_EDIT:
            return new EditProfileCommand(arguments);
        case Commands.COMMAND_HELP:
            return new HelpCommand(arguments);
        case Commands.COMMAND_DELETE:
            return new DeleteCommand(arguments);
        case Commands.COMMAND_BYE:
            return new ExitCommand(arguments);
        default:
            return new InvalidCommand(userInput);
        }
    }
}
