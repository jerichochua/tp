package fitr.command;

import fitr.list.GoalList;
import fitr.Recommender;
import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList, Recommender recommender) {
        Ui.printFormatError(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
