package fitr.command;

import fitr.Calorie;
import fitr.Food;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;

import java.io.IOException;

public class AddFoodCommand extends Command {
    public AddFoodCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        command = command.split(" ", 2)[1];
        try {
            String nameOfFood = command.split("/", 2)[0];
            nameOfFood = nameOfFood.trim();
            command = command.split("/", 2)[1];
            if (command.split(" ").length == 1) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                foodlist.addFood(new Food(nameOfFood, amountOfCalories));
                storage.writeFoodList(foodlist);
                Ui.showAdd("The following food has been added: " + nameOfFood);
            } else if (command.split(" ").length == 2) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                int amountOfFood = Integer.parseInt(command.split(" ", 2)[1]);
                foodlist.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood));
                storage.writeFoodList(foodlist);
                Ui.showAdd("The following food has been added: " + nameOfFood);
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry calories have to be a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Please key in the correct format");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
