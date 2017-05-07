package cz.najman;

import java.util.*;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Translator {

    private static final String WAY = "way";
    private static final String AY = "ay";
    private static final String HYPHEN = "-";
    private static final String CONSANANTS = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    private static final String VOWELS = "aeiouAEIOU";
    private static final String PUNCT_REGEX = "\\p{Punct}";
    private static final String CONTAINTS_PUNCT_REGEX = ".*\\p{Punct}.*";
    
    private List<Integer> capitalPositionsBackup = new ArrayList<>();
    private Map<Integer, Character> puctuationsBackup = new HashMap<>();

    public String translate(String input) {
        if (input.contains(HYPHEN)) {
            return translateMultiWord(input);
        }
        return translateWord(input);
    }
    
    private String translateMultiWord(String input) {
        return translateWord(input.substring(0, input.indexOf(HYPHEN))) + HYPHEN + translate(input.substring(input.indexOf(HYPHEN) + 1));
    }

    private String translateWord(String word) {
        if (!isAbleForTranslation(word)) {
            return word;
        }

        backupCapitals(word);
        backupPuctuations(word);
        word = word.replaceAll(PUNCT_REGEX, "");
        
        if (startWithVowel(word)) {
            word = word + WAY;
        } else {
            word = word.substring(1) + toLowerCase(word.charAt(0)) + AY;
        }

        if (!puctuationsBackup.isEmpty()) {
            word = loadPuctuations(word);
        }

        if (!capitalPositionsBackup.isEmpty()) {
            word = loadCapitals(word);
        }

        return word;
    }

    private boolean isAbleForTranslation(String word) {
        return word != null && word.length() != 0 && !isWayInTheEnd(word)
                && (startWithConsonant(word) || startWithVowel(word));
    }

    private Boolean isWayInTheEnd(String input) {
        return input.indexOf(WAY) + WAY.length() == input.length();
    }

    private boolean startWithConsonant(String input) {
        return isConsanant(input.charAt(0));
    }

    private static boolean isConsanant(char c) {
        return CONSANANTS.contains(valueOf(c));
    }

    private boolean startWithVowel(String input) {
        return isVowel(input.charAt(0));
    }

    private static boolean isVowel(char c) {
        return VOWELS.contains(valueOf(c));
    }

    private void backupPuctuations(String word) {
        puctuationsBackup.clear();
        if (contaisPuct(word)) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(word.length() - 1 - i);
                if (valueOf(letter).matches(PUNCT_REGEX)) {
                    puctuationsBackup.put(i, letter);
                }
            }
        }
    }

    private boolean contaisPuct(String input) {
        return input.matches(CONTAINTS_PUNCT_REGEX);
    }

    private void backupCapitals(String input) {
        capitalPositionsBackup.clear();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                capitalPositionsBackup.add(i);
            }
        }
    }

    private String loadPuctuations(String word) {
        int length = word.length() + puctuationsBackup.size();
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iter = new StringBackwardIterator(word);
        for (int i = 0; i < length; i++) {
            sb.append(puctuationsBackup.get(i) != null ? puctuationsBackup.get(i) : iter.next());
        }
        return sb.reverse().toString();
    }

    private String loadCapitals(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String letter = valueOf(input.charAt(i));
            sb.append(capitalPositionsBackup.contains(i) ? letter.toUpperCase() : letter.toLowerCase());
        }
        return sb.toString();
    }
}
