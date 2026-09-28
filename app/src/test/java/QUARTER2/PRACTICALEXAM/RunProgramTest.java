package quarter2.practicalexam;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class RunProgramTest {

    @Test
    public void testRunProgram() {
        InputStream originalIn = System.in;
        try {
            String input = "4\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            main(new String[]{});
        } finally {
            System.setIn(originalIn);
        }
    }

    public static void main(String[] args) {

        Scanner menuinput = new Scanner(System.in);

        MainMenu mainMenu = new MainMenu();
        mainMenu.Menu(menuinput);

    }
}
