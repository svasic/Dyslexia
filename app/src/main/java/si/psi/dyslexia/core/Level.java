package si.psi.dyslexia.core;


import si.psi.dyslexia.R;

public enum Level {
    LEVEL1(R.string.level_1_title, R.string.level_1_description),
    LEVEL2(R.string.level_2_title, R.string.level_2_description),
    LEVEL3(R.string.level_3_title, R.string.level_3_description),
    LEVEL4(R.string.level_4_title, R.string.level_4_description),
    LEVEL5(R.string.level_5_title, R.string.level_5_description),
    LEVEL6(R.string.level_6_title, R.string.level_6_description),
    LEVEL7(R.string.level_7_title, R.string.level_7_description);

    int title;
    int description;

    Level(int title, int description) {
        this.title = title;
        this.description = description;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }
}
