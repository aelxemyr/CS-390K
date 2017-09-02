import java.util.Set;
import java.util.SortedSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * Devious (cheating) implementation of HangmanManager.
 *
 * @author Bennett Alex Myers
 * @version 12/8/2016
 */
public class Hangman implements HangmanManager {

    /** The number of guesses the player has remaining. */
    private int guessesLeft;
    /** The set of letters that have been guessed by the user. */
    private SortedSet<Character> guesses;
    /** The set of words under consideration in the game. */
    private TreeSet<String> words;

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
                   "Word length must be positive and max must be nonnegative.");
        }
        this.guessesLeft = max;
        this.guesses = new TreeSet<>();
        this.words = dictionary
                .stream()
                .filter(word -> word.length() == length)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Accesses the set of candidate goal words.
     *
     * @return the set of candidate goal words
     */
    public Set<String> words() {
        return this.words;
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
        if (this.words.isEmpty()) {
            throw new IllegalStateException("There is no goal word.");
        }
        return pattern(this.words.iterator().next());
    }

    /**
     * Return the hangman-style display pattern of letters and dashes
     * appropriate to the given word based on the letters already guessed.
     *
     * @param word the word whose hangman-style pattern is to be returned
     * @return the hangman-style pattern of letters and dashes associated with
     *         the given word
     */
    private String pattern(final String word) {
        return word.chars()
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
        if (this.guessesLeft == 0 || this.words.isEmpty()) {
            throw new IllegalStateException("Illegal game state");
        }
        if (this.guesses.contains(guess)) {
            throw new IllegalArgumentException("Guess has already been made");
        }
        this.guesses.add(guess);
        updateWords();
        String exampleWord = this.words.iterator().next();
        if (exampleWord.contains("" + guess)) {
            return countLetterOccurrences(exampleWord, guess);
        } else {
            this.guessesLeft--;
            return 0;
        }
    }

    /**
     * Update the set of candidate words under consideration by this hangman
     * game based on the current state.
     */
    private void updateWords() {
        this.words = getPatternMap()
                .values()
                .stream()
                .max((s1, s2) -> (s1.size() - s2.size()))
                .map(TreeSet::new)
                .get();
    }

    /**
     * Return a mapping of hangman-style patterns to the set of candidate
     * words for which that pattern is valid over the current words under
     * consideration by the hangman game.
     *
     * @return a mapping of patterns to words for which that pattern is valid,
     *         based on the letters that have been guessed and the current set
     *         of candidate words
     */
    private Map<String, Set<String>> getPatternMap() {
        Map<String, Set<String>> patternMap = new HashMap<>();
        for (String word : this.words) {
            String pattern = pattern(word);
            if (patternMap.containsKey(pattern)) {
                patternMap.get(pattern).add(word);
            } else {
                patternMap.put(pattern,
                        new TreeSet<>(Collections.singleton(word)));
            }
        }
        return patternMap;
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

