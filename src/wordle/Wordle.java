package wordle;

import project20280.hashtable.ChainHashMap;

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

//    Map<Character, Integer> letterFrequency = new HashMap<>();
//      Map<String, Integer> wordScores = new HashMap<>();
    ChainHashMap<Character, Integer> letterFrequency = new ChainHashMap<>();
    ChainHashMap<String, Integer> possible_words = new ChainHashMap<>();


    Wordle() {
        this.dictionary = readDictionary(fileName);
        calculateLetterFrequency();
        calculateWordScores();
    }

    public static void main(String[] args) {

        Wordle game = new Wordle();
        String target = game.getRandomTargetWord();
        game.printLoadFactor(); // Print load factor after some gameplay
        game.play("abbey");

    }

    void calculateLetterFrequency() {
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                letterFrequency.put(c, letterFrequency.getOrDefault(c, 0) + 1);
            }
        }
    }

    public int play(String target) {
        String guess = "keeps"; // Starting with the first guess

        int guessCount = 0;

        while (true) {
            guessCount++;
            if (guess.equals(target)) {
                win(target);
                return guessCount;
            }

            String[] hint = generateHint(guess, target);
            System.out.println("collisions: " + possible_words.countcollisions());
            System.out.println("Guess:\n "  + guess + ",\n Hint: " + Arrays.toString(hint) + "\n");


            filterDictionaryBasedOnHint(guess, target);
            if (possible_words.isEmpty()) {
                lost(target); // This should never happen if the code is correct
                return guessCount; // Return guess count when no words left (fail-safe)
            }

            guess = getNextBestGuess();
        }
    }


    String[] generateHint(String guess, String target) {
        String[] hint = new String[5];
        Arrays.fill(hint, "⬛");
        boolean[] matched = new boolean[target.length()];

        for (int k = 0; k < 5; k++) {
            if (guess.charAt(k) == target.charAt(k)) {
                hint[k] = "\uD83D\uDFE9";
                matched[k] = true;
            }
        }

        for (int k = 0; k < 5; k++) {
            if (hint[k].equals("⬛")) {
                for (int j = 0; j < target.length(); j++) {
                    if (guess.charAt(k) == target.charAt(j) && !matched[j]) {
                        hint[k] = "\uD83D\uDFE8";
                        matched[j] = true;
                        break;
                    }
                }
            }
        }
        return hint;
    }

    public void filterDictionaryBasedOnHint(String guess, String target) {
        String[] hints = generateHint(guess, target);
        List<String> toRemove = new ArrayList<>();

        for (String word : possible_words.keySet()) {
            String[] wordHints = generateHint(guess, word);
            if (!doHintsMatch(wordHints, hints)) {
                toRemove.add(word);
            }
        }

        // Now remove the keys
        for (String key : toRemove) {
            possible_words.remove(key);
        }
    }

    private boolean doHintsMatch(String[] wordHints, String[] targetHints) {
        for (int i = 0; i < targetHints.length; i++) {
            if (!wordHints[i].equals(targetHints[i])) {
                return false;
            }
        }
        return true;
    }

    String getNextBestGuess() {
        String bestGuess = null;
        int maxScore = Integer.MIN_VALUE;

        for (project20280.interfaces.Entry<String, Integer> entry : possible_words.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                bestGuess = entry.getKey();
            }
        }

        return bestGuess;
    }

    void calculateWordScores() {
        for (String word : dictionary) {
            int score = 0;
            for (char c : word.toCharArray()) {
                score += letterFrequency.get(c);
            }
            possible_words.put(word, score);
        }
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

    public void printLoadFactor() {
        double loadFactor = possible_words.calculateLoadFactor();
        System.out.println("Current Load Factor: " + loadFactor);
    }

}