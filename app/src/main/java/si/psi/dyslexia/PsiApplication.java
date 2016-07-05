package si.psi.dyslexia;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.LinkedList;
import java.util.Locale;

import si.psi.dyslexia.components.ComponentManager;
import si.psi.dyslexia.components.UserComponent;
import si.psi.dyslexia.core.Language;
import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.core.Symbol;
import si.psi.dyslexia.core.User;

/**
 * Created by SVasic on 26.8.2015.
 */
public class PsiApplication extends Application {

    private ComponentManager componentManager;

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.initialize(this);
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // TODO: check language code, save to preferences and refresh codegenerator language
    }
}
