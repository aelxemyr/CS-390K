import java.util.Set;
import java.util.SortedSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Basic (fair) implementation of HangmanManager.
 *
 * @author Bennett Alex Myers
 * @version 12/2/2016
 */
public class Hangman implements HangmanManager {

    /** The number of guesses the player has remaining. */
    private int guessesLeft;
    /** The set of letters that have been guessed by the user. */
    private SortedSet<Character> guesses;
    /** The word chosen to be guessed by the player. */
    private String goalWord;

    /**
     * Initialize a new hangman game using a given dictionary, word length, and
     * number of wrong guesses allowed.
     *
     * @param dictionary a list of words from which to choose for a hangman
     *                   game
     * @param length the length of the word to be used for the game
     * @param max the maximum number of wrong guesses allowed
     * @throws IllegalArgumentException if <code>length</code> is less than 1
     *                                  or if <code>max</code> is less than 0
     */
    public Hangman(final List<String> dictionary,
                   final int length,
                   final int max) throws IllegalArgumentException {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException(
                "Word length must be positive and number of wrong guesses"
                        + " allowed must be nonnegative.");
        }
        this.guessesLeft = max;
        this.guesses = new TreeSet<>();
        this.goalWord = chooseGoalWord(dictionary, length);
    }

    /**
     * Utility method that randomly chooses a word of a given length, given a
     * list of words.
     *
     * @param dictionary a list of words from which to randomly choose a word
     * @param length the length of the desired word
     * @return a randomly chosen word of the given length from the given
     *         dictionary
     */
    private static String chooseGoalWord(final List<String> dictionary,
                                         final int length) {
        List<String> wordList = dictionary
                .stream()
                .filter(word -> word.length() == length)
                .collect(Collectors.toCollection(ArrayList::new));
        if (wordList.size() == 0) {
            return null;
        }
        Collections.shuffle(wordList);
        return wordList.get(0);
    }

    /**
     * Accesses the set of candidate goal words.
     *
     * @return the set of candidate goal words
     */
    public Set<String> words() {
        if (this.goalWord == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(this.goalWord);
    }

    /**
     * Accesses the number of allowable wrong guesses.
     *
     * @return the number of wrong guesses the user has left
     */
    public int guessesLeft() {
        return this.guessesLeft;
    }

    /**
     * Accesses the set of letters already guessed by the user.
     *
     * @return the current set of letters guessed by the user
     */
    public SortedSet<Character> guesses() {
        return this.guesses;
    }

    /**
     * Return the hangman-style display pattern of letters and dashes
     * appropriate to the current state based on the letters already
     * guessed and the goal.
     *
     * @throws IllegalStateException if there is no goal word
     * @return the hangman-style pattern to be displayed to the user
     */
    public String pattern() throws IllegalStateException {
        return this.goalWord.chars()
                .mapToObj(c -> (char) c)
                .map(this::patternize)
                .collect(StringBuilder::new,
                         StringBuilder::append,
                         StringBuilder::append)
                .toString();
    }

    /**
     * Returns either the given character if the character has been guessed or
     * a dash '-' if the given character has not been guessed.
     *
     * @param c the character being considered
     * @return the character if it has been guessed, a dash otherwise
     */
    private char patternize(final char c) {
        if (this.guesses.contains(c)) {
            return c;
        } else {
            return '-';
        }
    }

    /**
     * Record state changes based on new letter guess.
     *
     * @throws IllegalStateException if no guesses left or no goal word
     * @throws IllegalArgumentException if letter is already guessed
     * @param guess the letter being guessed
     *   [Precondition: must be lower-case letter]
     *   [Precondition: must not be among letters already guessed]
     * @return the number of occurrences of the guessed letter in the goal
     */
    public int record(final char guess)
            throws IllegalStateException, IllegalArgumentException {
        if (this.guessesLeft == 0 || this.goalWord == null) {
            throw new IllegalStateException("Illegal game state");
        }
        if (this.guesses.contains(guess)) {
            throw new IllegalArgumentException("Guess has already been made");
        }
        this.guesses.add(guess);
        if (this.goalWord.contains("" + guess)) {
            return countLetterOccurrences(this.goalWord, guess);
        } else {
            this.guessesLeft--;
            return 0;
        }
    }

    /**
     * Utility method for counting the occurrences of a letter in a word.
     *
     * @param word a word from which to count letter occurrences
     * @param letter the letter whose occurrences to count
     * @return the number of times the given letter appears in the given word
     */
    private static int countLetterOccurrences(final String word,
                                              final char letter) {
        return (int) word
                .chars()
                .filter(c -> (c == letter))
                .count();
    }
}

