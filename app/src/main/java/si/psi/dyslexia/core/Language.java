package si.psi.dyslexia.core;

public class Language {
    private String code;
    private Symbol[] alphabet;

    public Language(String code, Symbol[] alphabet) {
        this.code = code;
        this.alphabet = alphabet;
    }

    public String getCode() {
        return code;
    }

    public Symbol[] getAlphabet() {
        return alphabet;
    }

    public boolean containsAlphabetLetter(String letter) {
        for (Symbol symbol : alphabet) {
            if (symbol.isLetter(letter)) return true;
        }
        return false;
    }

}
