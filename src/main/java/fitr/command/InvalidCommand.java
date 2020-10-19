package fitr.command;

import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        this.arguments = userInput;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user) {
        Ui.printFormatError(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
