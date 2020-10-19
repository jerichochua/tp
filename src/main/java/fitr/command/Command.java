package fitr.command;

import fitr.storage.Storage;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

public abstract class Command {
    protected String arguments;

    public abstract void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user);

    public abstract boolean isExit();
}
