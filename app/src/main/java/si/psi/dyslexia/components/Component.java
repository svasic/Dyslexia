package si.psi.dyslexia.components;

import android.content.Context;
import android.content.SharedPreferences;

import si.psi.dyslexia.PsiApplication;

public abstract class Component {

    private PsiApplication app;

    public Component(PsiApplication application) {
        this.app = application;
    }

    public PsiApplication getApp() {
        return app;
    }

    public void saveToPreferences() {
        SharedPreferences preferences = app.getSharedPreferences("PSI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        fillToPreferences(editor);
        editor.commit();
    }

    public abstract void fillToPreferences(SharedPreferences.Editor editor);



}
