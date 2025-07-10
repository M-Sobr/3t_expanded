import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        System.out.println("Enter an option to play a game of tic-tac-toe with:");
        System.out.println("1) 3x3 game");
        System.out.println("2) 4x4 game");
        System.out.println("3) 9x9 game");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = (scanner.nextLine()).trim();
            if (input.equals("1")) { // 3x3 game
                new Game3x3().run();
                break;
            } else if (input.equals("2")) { // 4x4 game
                new Game4x4().run();
                break;
            } else if (input.equals("3")) { // 9x9 game
                new Game9x9().run();
                break;
            }
        }
        scanner.close();
    }
}