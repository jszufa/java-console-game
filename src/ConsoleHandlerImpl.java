import java.util.Scanner;

public class ConsoleHandlerImpl implements ConsoleHandler {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String readInput() {
        String input = scanner.next();
        return input;
    }

    @Override
    public void displayOutput(String output) {
        System.out.print(output);
    }

    @Override
    public void displayOutputLn(String output) {
        System.out.println(output);
    }

    @Override
    public void displayOutputLn() {
        System.out.println();
    }
}
