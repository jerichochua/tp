package seedu.duke;

import java.io.IOException;

public class Fitr {
    private Storage storage;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private User user;

    public Fitr(String filePathOfUserConfig, String filePathOfFoodList, String filePathOfExerciseList){
        foodList = new FoodList();
        exerciseList = new ExerciseList();
        try {
            storage = new Storage(filePathOfUserConfig, filePathOfFoodList, filePathOfExerciseList);
        } catch (IOException e) {
            System.out.println("Theres no file");
        }
    }

    public void run() {
        boolean isExit = false;
        user = new User();
        while(!isExit) {
            String userInput = UI.read();
            Command c = Parser.parse(userInput);
            c.execute(foodList, exerciseList, storage);
            isExit = c.isExit();
        }
        UI.printExitMessage();
    }

    public static void main(String[] args) {
        new Fitr("userConfig.txt", "foodList.txt", "exerciseList.txt").run();
    }
}
