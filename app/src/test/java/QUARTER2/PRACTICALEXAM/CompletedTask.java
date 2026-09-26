package QUARTER2.PRACTICALEXAM;

import java.util.Scanner;

public class CompletedTask{

    public static void CompletedTaskComponent() {

        //Stores that the child is done with their task.
        String CompletedTask = "done";

        //Stores that the mother knows their child's task is done.
        String finishKidAssignment = "confirmed";

        System.out.println("Child's task status:" + CompletedTask);
        System.out.println("Mother's confirmation" + finishKidAssignment);
    }

    public static void main(String[] args)
    {
        CompletedTaskComponent();
    }
}
