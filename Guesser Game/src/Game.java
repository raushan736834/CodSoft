import java.util.Random;
import java.util.Scanner;

public class Game {
    public static int generate(){
        Random r = new Random();
        return r.nextInt(101); // Generates a random number between 0 and 100
    }

    public static boolean validate(int guess, int generated){
        if (guess == generated){
            System.out.println("You Guessed the Correct Number");
            return true;
        } else if (guess > generated) {
            System.out.println("Guessed No. is greater than generated No.");
        } else {
            System.out.println("Guessed No. is smaller than generated No.");
        }
        return false;
    }

    public static void rule(){
        System.out.println("Rules of the Game are:----");
        System.out.println("Each Game has 3 Rounds.");
        System.out.println("Each Round has 5 attempts to guess the Generated Number");
        System.out.println("If you can't guess the number in given attempts you lose the game");
        System.out.println("Scores according to Attempt are");
        System.out.println("1 Attempt - 50 Score");
        System.out.println("2 Attempt - 40 Score");
        System.out.println("3 Attempt - 30 Score");
        System.out.println("4 Attempt - 20 Score");
        System.out.println("5 Attempt - 10 Score");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        rule();
        Scanner sc = new Scanner(System.in);
        int totalScore = 0;

        // Game consists of 3 rounds
        for (int i = 1; i <= 3; i++) {
            System.out.printf("Round %d\n", i);
            boolean roundWon = false;
            int generated = generate();
            int attempt;
            // Each round has 5 attempts
            for (attempt = 1; attempt <= 5; attempt++) {
                System.out.printf("Attempt %d: Guess the Generated Number: ", attempt);
                int guess = sc.nextInt();
                if (validate(guess, generated)) {
                    roundWon = true;
                    break;
                }
            }

            // Update total score based on the result of the round
            if (roundWon) {
                int roundScore = 50 - (attempt - 1) * 10;
                totalScore += roundScore;
                System.out.printf("You Scored %d in Round %d\n", roundScore, i);
            } else {
                System.out.println("You Lost the Round");
            }
        }

        System.out.printf("Total Score: %d\n", totalScore);
    }
}