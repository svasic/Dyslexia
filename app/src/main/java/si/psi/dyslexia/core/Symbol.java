package si.psi.dyslexia.core;

public class Symbol {

    String upperCase;
    String lowerCase;

    public Symbol(String upperCase, String lowerCase) {
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
    }

    public String getUpperCase() {
        return upperCase;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    public boolean isLetter(String letter) {
        return upperCase.equals(letter) || lowerCase.equals(letter);
    }

}
