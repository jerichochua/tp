package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.arguments = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user) {
        try {
            arguments = arguments.split(" ", 2)[1];
            String type = arguments.split(" ", 2)[0];
            if (type.equals("food")) {
                int deletionIndex = Integer.parseInt(arguments.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + foodlist.getFood(deletionIndex - 1).getFoodName());
                foodlist.deleteFood(deletionIndex - 1);
                storage.writeFoodList(foodlist);
            } else if (type.equals("exercise")) {
                int deletionIndex = Integer.parseInt(arguments.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + exerciseList.getExercise(deletionIndex - 1).getNameOfExercise());
                exerciseList.deleteExercise(deletionIndex - 1);
                storage.writeExerciseList(exerciseList);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printCustomError("Sorry that index does not exist in the list");
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry index deletion must be an number");
        } catch (IOException e) {
            Ui.printCustomError("Sorry there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
