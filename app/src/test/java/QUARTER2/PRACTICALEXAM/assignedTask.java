package quarter2.practicalexam;

import java.util.Scanner;

public class assignedTask {

    public static void main(String[] args) {
        assignedTaskComponent();
    }

    public static void assignedTaskComponent() {

        Scanner scanner = new Scanner(System.in);

        // Stores the name or description of the task.
        String assignedTask;

        // Stores the name or ID of the child.
        String kidId;

        System.out.println("===== ASSIGNED TASK =====");

        System.out.print("Enter Child ID/Name: ");
        kidId = scanner.nextLine();

        System.out.print("Enter Assigned Task: ");
        assignedTask = scanner.nextLine();

        System.out.println("\n===== TASK DETAILS =====");
        System.out.println("Child ID/Name: " + kidId);
        System.out.println("Assigned Task: " + assignedTask);

        scanner.close();
    }
}
