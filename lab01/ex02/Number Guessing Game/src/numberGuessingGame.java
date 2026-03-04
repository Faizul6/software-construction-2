import java.util.Random;
import java.util.Scanner;

public class numberGuessingGame {
    public static void main(String[] args) {
        //Number guessing game

        Random rand = new Random();
        int randomNumber = rand.nextInt(1,100) ; // generates a number between 1-100
        int attempts = 0;
        int userGuess;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the guessing game");
        System.out.println("Guess a number between 1-100");
        do {
            System.out.println("Enter a guess");
            userGuess = scanner.nextInt();
            attempts++;
            if (userGuess > randomNumber) {
                System.out.println("Too big!!, guess again");
            }
            else if (userGuess < randomNumber) {
                System.out.println("Too small!!, guess again");
            }
            else{
                System.out.println(" Congratulations, you guessed right!");
                System.out.println(" # of attempts: " + attempts);
            }

        } while (userGuess != randomNumber); //Repeats loop until we guess right
        scanner.close();
    }
}
