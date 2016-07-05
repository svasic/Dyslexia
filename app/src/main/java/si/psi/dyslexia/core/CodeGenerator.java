package si.psi.dyslexia.core;

import java.util.Arrays;
import java.util.LinkedList;

import si.psi.dyslexia.PsiApplication;

public class CodeGenerator {

    private CodeGenerator() {
    }

    private static CodeGenerator instance;

    public static CodeGenerator getInstance(PsiApplication application) {
        if (instance == null) {
            instance = new CodeGenerator();
        }
        return instance;
    }

    public static CodeGenerator getInstance() {
        return instance;
    }

    private Language language;

    public String toRacioReadable(String source, int level) {
        String converted = "";

        // split source to words
        String[] words = source.split("\\W+");

        // TODO: get mode from preferences

        for (String word : words) {
            String racioWord = replaceLettersWithNumbersInWord(word, MODE_EVERY_2ND);
            source.replace(word, racioWord);
        }

        return converted;
    }

    // modes which defines algorithm in replacing letters with numbers
    int MODE_EVERY_2ND = 0;
    int MODE_ALL = 1;

    private String replaceLettersWithNumbersInWord(String word, int mode) {
        String converted = "";
        Symbol[] alphabet = language.getAlphabet();

        for (int i = 0; i < word.length(); i++) {
//            char letter = word.charAt(i);
//            int order =
//            if (order != -1) {
//                if (mode == MODE_ALL
//                        || (mode == MODE_EVERY_2ND && i%2 == 0)) {
//                    converted = converted.concat(String.valueOf(order));
//                }
//                else {
//                    converted.concat(String.valueOf(letter));
//                }
//            }
        }
        return converted;
    }

    public Symbol getChar(int letterOrder) {
        return language.getAlphabet()[letterOrder];
    }

}
