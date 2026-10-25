package QUARTER2.PRACTICALEXAM;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        Scanner menuinput = new Scanner(System.in);

        MainMenu mainMenu = new MainMenu();
        mainMenu.Menu(menuinput);

    }

    public void Menu(Scanner menuinput) {

        boolean choosing = true;

        do {

            System.out.println("=====MAIN MENU=====");
            System.out.println("1. TaskDetails");
            System.out.println("2. AssignedTask");
            System.out.println("3. CompletedTask");
            System.out.println("4. Exit");

            System.out.print("Please choose an option (1, 2, 3, 4): ");

            String choice = menuinput.nextLine();

            switch (choice) {
                case "1":

                    System.out.println("TaskDetails selected.");
                    choosing = false;

                    break;
                case "2":

                    System.out.println("AssignedTask selected.");
                    choosing = false;

                    break;
                case "3":

                    System.out.println("CompletedTask selected.");
                    choosing = false;

                    break;
                case "4":

                    System.out.println("Goodbye!");
                    choosing = false;

                    break;
                default:

                    System.out.println("===================");
                    System.out.println("Invalid choice please try again.");
                    System.out.println("===================");

                    break;
            }

        } while (choosing);

    }

}
