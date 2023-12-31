/**Author - Deepanshu Maliyan**/
/**Task 1 - Number Guessing Game in Java #CODSOFT #INTERNSHIP**/
import java.lang.*;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    int defaultLowerBound = 1;
    int defaultUpperBound = 100;
    int maxAttempts = 14; // Initial attempts
    int score = 0;
    int roundNumber = 0;
    int highestScore = 0;

    System.out.println("\n=== Number Guessing Game ===\n");

    boolean playAgain = true;

    do {
      roundNumber++;

      System.out.println("\n***** Level " + (roundNumber - 1) + " *****\n"); // Display the level at the start of each
                                                                             // round

      int lowerBound = getUserInput("\nEnter lower bound for the number range (default is " + defaultLowerBound + "): ",
          defaultLowerBound);
      int upperBound = getUserInput("Enter upper bound for the number range (default is " + defaultUpperBound + "): ",
          defaultUpperBound);

      int secretNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

      System.out.printf("\nRound %d - Range: %d to %d\n", roundNumber, lowerBound, upperBound);
      System.out.println("Attempts allowed: " + maxAttempts);
      System.out.println("====================================\n");

      int attempts = 0;
      boolean guessedCorrectly = false;

      while (attempts < maxAttempts) {
        System.out.print("\nEnter your guess (type 'hint' for a hint): ");
        String input = scanner.next();

        if (input.equalsIgnoreCase("hint")) {
          giveHint(secretNumber);
          continue;
        }

        int playerGuess;
        try {
          playerGuess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
          System.out.println("\nInvalid input. Please enter a number or 'hint'.");
          continue;
        }

        attempts++;

        if (playerGuess == secretNumber) {
          System.out.println("\nCongratulations! You guessed the number in " + attempts + " attempts.");
          score += 5; // Add 5 points for every correct guess
          if (score > highestScore) {
            highestScore = score;
          }
          guessedCorrectly = true;
          break;
        } else if (playerGuess < secretNumber) {
          System.out.println("\nToo low! Try again.");
        } else {
          System.out.println("\nToo high! Try again.");
        }
      }

      if (!guessedCorrectly) {
        System.out.println("\nSorry, you've used all your attempts. The correct number was " + secretNumber);
      }

      System.out.println("\n==== Round Summary ====");
      System.out.println("Your current score: " + score);
      System.out.println("Highest score so far: " + highestScore);
      System.out.println("=======================\n");

      System.out.print("Do you want to play another round? (yes/no): ");
      String playAgainResponse = scanner.next().toLowerCase();

      switch (playAgainResponse) {
        case "yes":
          playAgain = true;
          maxAttempts--; // Decrease the attempts for the next round
          break;
        case "no":
          playAgain = false;
          break;
        default:
          System.out.println("\nInvalid input. Please enter yes or no.");
          break;
      }

    } while (playAgain);

    System.out.println("\n=== Thank you for playing! ===");
    System.out.println("Your final score: " + score);
    System.out.println("Highest score overall: " + highestScore);

    scanner.close();
  }

  private static int getUserInput(String message, int defaultValue) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(message);
    String userInput = scanner.nextLine();
    return userInput.isEmpty() ? defaultValue : Integer.parseInt(userInput);

  }

  private static void giveHint(int secretNumber) {
    System.out.println("\nHint: The number is " + (secretNumber % 2 == 0 ? "even." : "odd."));
  }

}
