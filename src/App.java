/*
 * Created on 2024-02-11
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class App {

    private static Scanner scanner;
    private static boolean readingDataFromFile = true;

    public static void main(String[] args) {
        // Print the first input argument
        System.out.println(Arrays.toString(args));
        // Check the argument's length
        if (args.length > 0) {
            Cat[] cats = readCatsFromFile(args[0]);

            if (cats != null) {
                /* TODO: type */ adoptionTable = generateAdoptionTable(cats);
                printAdoptionTable(adoptionTable);
                sortAdoptions(adoptionTable);
                printAdoptionTable(adoptionTable);
            }
        }
        scanner.close();
    }

    public static void printAdoptionTable(/* TODO: type */ adoptionTable) {
        System.out.println("\nAdoption Table:");
        System.out.println("# | Cat\t\t | Owner\t | Year");
        System.out.println("----------------------------------------");
        
        // TODO: LAB06 - Task 1
    }

    public static /* TODO: return type */ generateAdoptionTable(Cat[] cats) {
        // TODO: LAB06 - Task 1
        
        return null;
    }

    public static void sortAdoptions(/* TODO: type*/ adoptionTable) {
        // TODO: LAB06 - Task 2
    }

    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * ! NO NEED TO TOUCH THE CODE BELOW !
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */

    public static Cat[] readCatsFromFile(String fileName) {
        try {
            File file = new File(fileName);
            scanner = new Scanner(file);

            // Count all lines in the file, divide by 6 to determine the number of cats
            int lineCount = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }

            int numberOfCats = lineCount / 6;

            // Reinitialize scanner
            scanner = new Scanner(file);

            Cat[] cats = new Cat[numberOfCats];
            for (int i = 0; i < numberOfCats && scanner.hasNextLine(); i++) {
                Cat cat = readCatFromInput();
                cats[i] = cat;
            }
            System.out.printf("Read %d cats%n", cats.length);
            return cats;
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file; " + e);
            return null;
        }
    }

    // Read cat object from user input
    public static Cat readCatFromInput() {
        Owner owner = promptForOwner();
        Cat userCat = promptForCat();

        if (userCat != null) {
            userCat.adopt(owner);
            if (promptForStory()) {
                Story story = promptForStoryDetails();
                userCat.setFunnyStory(story);
            }
        }

        return userCat;
    }

    // Helper methods to prompt the user for input
    private static Owner promptForOwner() {
        String ownerName = scanner.nextLine();
        // System.out.println("Owner name:\t" + ownerName);
        return new Owner(ownerName);
    }

    private static Cat promptForCat() {
        String name = scanner.nextLine();
        // System.out.println("Cat name:\t" + name);

        String furColor = scanner.nextLine();
        // System.out.println("Cat fur color:\t" + furColor);
        int age = 404; // Error code
        boolean validInput = false;
        while (!validInput) {
            try {
                age = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                if (!readingDataFromFile) {
                    System.out.println(e.toString() + " -- Invalid input, please input your cat's age as a number.");
                    scanner.nextLine();
                } else {
                    System.out.println(e.toString() + " -- Invalid input, not a number.");
                    validInput = true;
                }
            }
        }
        // System.out.println("Cat age:\t" + age);

        scanner.nextLine();
        return new Cat(name, furColor, age);
    }

    private static Story promptForStoryDetails() {
        String storyTitle = scanner.nextLine();
        // System.out.println("Story title:\t" + storyTitle);
        String storyDescription = scanner.nextLine();
        // System.out.println("Story:\t\t" + storyDescription);
        return new Story(storyTitle, storyDescription);
    }

    private static boolean promptForStory() {
        if (readingDataFromFile) {
            return true;
        }
        System.out.println("Do you want to share a funny story about your cat? (yes/no)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }
}
