# Lab 01 – Exercise 03  

## Roman Numeral Converter


In this exercise, I implemented a Roman Numeral Converter in Java.



The program converts Roman numerals into their corresponding integer values.  

This task focuses on working with strings, characters, conditionals, and basic algorithm logic.



---



## Task Overview



Roman numerals use seven different symbols:



- I = 1  

- V = 5  

- X = 10  

- L = 50  

- C = 100  

- D = 500  

- M = 1000  



Most of the time, Roman numerals are written from largest to smallest (left to right), and the values are added together.



Examples:



- "III" = 1 + 1 + 1 = 3  

- "LVIII" = 50 + 5 + 1 + 1 + 1 = 58  



However, there are special cases where a smaller numeral appears before a larger one.  

In this case, the smaller value is subtracted instead of added.



Examples:



- "IV" = 5 - 1 = 4  

- "IX" = 10 - 1 = 9  

- "XL" = 50 - 10 = 40  

- "XC" = 100 - 10 = 90  

- "CD" = 500 - 100 = 400  

- "CM" = 1000 - 100 = 900  



The program processes the Roman numeral string character by character and applies these rules.



---



## My Task



The program should:



1. Ask the user to enter a Roman numeral as a string.

2. Convert it into an integer using the rules described above.

3. Display the result.



Example test cases:



- "III" should return 3  

- "IV" should return 4  

- "IX" should return 9  

- "LVIII" should return 58  

- "MCMXCIV" should return 1994  



---



## Requirements



The implementation includes:



- Use of the `Scanner` class for user input.

- Processing the string character by character.

- Handling both normal addition and subtraction cases.

- Displaying clear and readable output.

