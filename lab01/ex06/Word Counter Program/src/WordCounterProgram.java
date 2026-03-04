import java.util.Scanner;

public class WordCounterProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.println("Enter a sentence or paragraph to analyze:");
        String text = scanner.nextLine();

        // Analyze the text
        analyzeText(text);

        scanner.close();
    }

    public static void analyzeText(String text) {
        // Basic character counts
        int totalCharsWithSpaces = text.length();
        int totalCharsWithoutSpaces = countCharsWithoutSpaces(text);
        int vowelCount = countVowels(text);
        int consonantCount = countConsonants(text);

        // Word and sentence analysis
        int wordCount = countWords(text);
        String longestWord = findLongestWord(text);
        int sentenceCount = countSentences(text);

        // Display results
        displayReport(text, totalCharsWithSpaces, totalCharsWithoutSpaces,
                vowelCount, consonantCount, wordCount, longestWord, sentenceCount);
    }

    // Count characters excluding spaces
    public static int countCharsWithoutSpaces(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }

    // Count vowels (case insensitive)
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // Count consonants (only English letters that are not vowels)
    public static int countConsonants(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (Character.isLetter(c) && !isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    // Helper method to check if a character is a vowel
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // Count words (split by whitespace and filter out empty strings)
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Find the longest word in the text
    public static String findLongestWord(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        // Remove punctuation and split into words
        String cleanedText = text.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleanedText.split("\\s+");

        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    // Count sentences based on punctuation marks (. ! ?)
    public static int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                count++;
            }
        }
        return count;
    }

    // Display the formatted report
    public static void displayReport(String originalText, int totalCharsWithSpaces,
                                     int totalCharsWithoutSpaces, int vowelCount,
                                     int consonantCount, int wordCount,
                                     String longestWord, int sentenceCount) {

        System.out.println("\n=== TEXT ANALYSIS REPORT ===");
        System.out.println("Original text: \"" + originalText + "\"");

        System.out.println("\nCharacter Statistics:");
        System.out.println("- Total characters (with spaces): " + totalCharsWithSpaces);
        System.out.println("- Total characters (without spaces): " + totalCharsWithoutSpaces);
        System.out.println("- Number of vowels: " + vowelCount);
        System.out.println("- Number of consonants: " + consonantCount);

        System.out.println("\nWord Statistics:");
        System.out.println("- Total number of words: " + wordCount);
        System.out.println("- Longest word: \"" + longestWord + "\"");

        System.out.println("\nSentence Statistics:");
        System.out.println("- Total number of sentences: " + sentenceCount);
    }
}
