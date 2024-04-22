package wordle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wordle {
//    String fileName = "wordle/resources/extended-dictionary.txt";
    String fileName = "wordle/resources/dictionary.txt";
    List<String> dictionary = null;
    final int num_guesses = 5;
    final long seed = 50;
    Random rand = new Random();

    static final String winMessage = "CONGRATULATIONS! YOU WON! :)";
    static final String lostMessage = "YOU LOST :( THE WORD CHOSEN BY THE GAME IS: ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";

    Map<Character, Integer> letterFrequency = new HashMap<>();
    Map<String, Integer> wordScores = new HashMap<>();

    Wordle() {
        this.dictionary = readDictionary(fileName);
        calculateLetterFrequency();
        calculateWordScores();
    }

    public static void main(String[] args) {
        Wordle game = new Wordle();
        String target = game.getRandomTargetWord();
        game.play(target);
    }

    void calculateLetterFrequency() {
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                letterFrequency.put(c, letterFrequency.getOrDefault(c, 0) + 1);
            }
        }
    }

    void calculateWordScores() {
        for (String word : dictionary) {
            int score = 0;
            for (char c : word.toCharArray()) {
                score += letterFrequency.get(c);
            }
            wordScores.put(word, score);
        }
    }

//    public void play(String target) {
//        String guess = "abbey"; // Starting with the first guess
//        List<String> possibleWords = new ArrayList<>(dictionary); // Clone the original dictionary to filter
//
//        for (int i = 0; i < num_guesses; ++i) {
//            if (guess.equals(target)) {
//                win(target);
//                return;
//            }
//
//            String[] hint = generateHint(guess, target);
//            System.out.println("Guess: " + guess + ", Hint: " + Arrays.toString(hint));
//
//            filterDictionaryBasedOnHint(guess, hint, possibleWords);
//            if (possibleWords.size() == 1) {
//                win(possibleWords.get(0));
//                return;
//            }
//            guess = getNextBestGuess(possibleWords);
//        }
//        lost(target);
//    }

    public int play(String target) {
        String guess = "slate"; // Starting with the first guess
        List<String> possibleWords = new ArrayList<>(dictionary); // Clone the original dictionary to filter
        int guessCount = 0;

        while (true) {
            guessCount++;
            if (guess.equals(target)) {
                win(target);
                return guessCount;
            }

            String[] hint = generateHint(guess, target);
            System.out.println("Guess:\n "  + guess + ",\n Hint: " + Arrays.toString(hint) + "\n");

            filterDictionaryBasedOnHint(guess, hint, possibleWords);
            if (possibleWords.isEmpty()) {
                lost(target);
                return guessCount; // Return guess count when no words left (fail-safe)
            }
            if (possibleWords.size() == 1) {
                win(possibleWords.get(0));
                return guessCount;
            }
            guess = getNextBestGuess(possibleWords);
        }
    }

    String[] generateHint(String guess, String target) {
        String[] hint = new String[5];
        Arrays.fill(hint, "_");
        boolean[] matched = new boolean[target.length()];

        for (int k = 0; k < 5; k++) {
            if (guess.charAt(k) == target.charAt(k)) {
                hint[k] = "+";
                matched[k] = true;
            }
        }

        for (int k = 0; k < 5; k++) {
            if (hint[k].equals("_")) {
                for (int j = 0; j < target.length(); j++) {
                    if (guess.charAt(k) == target.charAt(j) && !matched[j]) {
                        hint[k] = "o";
                        matched[j] = true;
                        break;
                    }
                }
            }
        }
        return hint;
    }
    void filterDictionaryBasedOnHint(String guess, String[] hint, List<String> possibleWords) {
        Iterator<String> it = possibleWords.iterator();
        while (it.hasNext()) {
            String word = it.next();
            String[] wordHint = generateHint(guess, word);
            if (!Arrays.equals(wordHint, hint)) {
                it.remove();
            }
        }
    }

    String getNextBestGuess(List<String> possibleWords) {
        return possibleWords.stream()
                .max(Comparator.comparingInt(wordScores::get))
                .orElse("error");
    }

    public String getRandomTargetWord() {
        return dictionary.get(rand.nextInt(dictionary.size()));
    }
    public void lost(String target) {
        System.out.println();
        System.out.println(lostMessage + target.toUpperCase() + ".");
        System.out.println();

    }
    public void win(String target) {
        System.out.println(ANSI_GREEN_BACKGROUND + target.toUpperCase() + ANSI_RESET);
        System.out.println();
        System.out.println(winMessage);
        System.out.println();
    }

    public String getGuess() {
        Scanner myScanner = new Scanner(System.in, StandardCharsets.UTF_8.displayName());  // Create a Scanner object
        System.out.println("Guess:");

        String userWord = myScanner.nextLine();  // Read user input
        userWord = userWord.toLowerCase(); // covert to lowercase

        // check the length of the word and if it exists
        while ((userWord.length() != 5) || !(dictionary.contains(userWord))) {
            if ((userWord.length() != 5)) {
                System.out.println("The word " + userWord + " does not have 5 letters.");
            } else {
                System.out.println("The word " + userWord + " is not in the word list.");
            }
            // Ask for a new word
            System.out.println("Please enter a new 5-letter word.");
            userWord = myScanner.nextLine();
        }
        return userWord;
    }

    public List<String> readDictionary(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line
            while ((strLine = reader.readLine()) != null) {
                wordList.add(strLine.toLowerCase());
            }
            //Close the input stream
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return wordList;
    }
}