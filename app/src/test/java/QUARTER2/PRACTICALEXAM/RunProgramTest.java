package QUARTER2.PRACTICALEXAM;

import java.util.Scanner;

public class RunProgramTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        taskManagement(scanner);

        scanner.close();
    }

    public static void taskManagement(Scanner scanner) {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== MAIN MENU =====");
            System.out.println("1. Assigned Task");
            System.out.println("2. Completed Task");
            System.out.println("3. Task Details");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    String assignedTask;
                    String kidId;

                    System.out.println();
                    System.out.println("===== ASSIGNED TASK =====");

                    System.out.print("Enter Kid ID: ");
                    kidId = scanner.nextLine();

                    System.out.print("Enter Assigned Task: ");
                    assignedTask = scanner.nextLine();

                    System.out.println();
                    System.out.println("Assigned Task: " + assignedTask);
                    System.out.println("Kid ID: " + kidId);

                    break;

                case "2":

                    String completedTask;
                    String finishKidAssignment;

                    System.out.println();
                    System.out.println("===== COMPLETED TASK =====");

                    System.out.print("Enter Completed Task: ");
                    completedTask = scanner.nextLine();

                    System.out.print("Enter Kid ID: ");
                    finishKidAssignment = scanner.nextLine();

                    System.out.println();
                    System.out.println("Completed Task: " + completedTask);
                    System.out.println("Kid ID: " + finishKidAssignment);

                    break;

                case "3":

                    String taskName;
                    String dueDate;
                    String status;

                    System.out.println();
                    System.out.println("===== TASK DETAILS =====");

                    System.out.print("Enter Task Name: ");
                    taskName = scanner.nextLine();

                    System.out.print("Enter Due Date: ");
                    dueDate = scanner.nextLine();

                    System.out.print("Enter Status: ");
                    status = scanner.nextLine();

                    System.out.println();
                    System.out.println("Task Name: " + taskName);
                    System.out.println("Due Date: " + dueDate);
                    System.out.println("Status: " + status);

                    break;

                case "4":

                    running = false;
                    System.out.println("Program ended.");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }
        }
    }
}
