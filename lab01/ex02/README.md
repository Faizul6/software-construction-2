# Lab 01 – Exercise 02  

\## Number Guessing Game



In this exercise, I implemented a simple Number Guessing Game in Java.



The program generates a random number and the player tries to guess it.  

This task focuses on practicing loops, conditionals, random number generation, and user input handling.



---



\## Task Overview



The program works as follows:



\- A random number between 1 and 100 (inclusive) is generated.

\- A welcome message explaining the game rules is displayed.

\- The user is repeatedly asked to guess the number.

\- After each guess, feedback is provided:

&nbsp; - "Too small!" if the guess is lower than the target number.

&nbsp; - "Too big!" if the guess is higher than the target number.

&nbsp; - "You guessed it!" if the guess is correct.

\- The number of attempts is tracked.

\- When the correct number is guessed, a congratulatory message is displayed along with the number of attempts.

\- The program ends after the correct guess.



The program assumes that user input is always a valid integer.



---



\## Requirements



The implementation includes:



\- Use of the `Random` class to generate numbers.

\- Use of the `Scanner` class to read user input.

\- A loop structure to continue asking for guesses until the correct number is entered.

\- Clear and user-friendly console messages.

\- Tracking and displaying the number of attempts.

