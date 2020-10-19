package fitr.command;

import fitr.Calorie;
import fitr.Food;
import fitr.exception.FitrException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class AddFoodCommand extends Command {
    public AddFoodCommand(String command) {
        this.arguments = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user) {
        arguments = arguments.split(" ", 2)[1];
        try {
            String nameOfFood = arguments.split("/", 2)[0];
            nameOfFood = nameOfFood.trim();
            if (nameOfFood.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            arguments = arguments.split("/", 2)[1];
            if (arguments.split(" ").length == 1) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(arguments.split(" ")[0]));
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                foodlist.addFood(new Food(nameOfFood, amountOfCalories));
                storage.writeFoodList(foodlist);
                Ui.printCustomMessage("The following food has been added: " + nameOfFood);
            } else if (arguments.split(" ").length == 2) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(arguments.split(" ")[0]));
                int amountOfFood = Integer.parseInt(arguments.split(" ", 2)[1]);
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                if (amountOfFood < 0) {
                    throw new FitrException();
                }
                foodlist.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood));
                storage.writeFoodList(foodlist);
                Ui.printCustomMessage("The following food has been added: " + nameOfFood);
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry calories have to be a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Please key in the correct format");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, the amount of food has to be a positive number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
