package si.psi.dyslexia.components;

import android.app.Application;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import si.psi.dyslexia.PsiApplication;
import si.psi.dyslexia.R;
import si.psi.dyslexia.core.InstanceComponent;
import si.psi.dyslexia.core.Language;
import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.core.Symbol;
import si.psi.dyslexia.core.User;

/**
 * Created by SVasic on 26.8.2015.
 */
public class ComponentManager {

    private static ComponentManager instance;

    private PsiApplication app;

    private Map<Class<? extends Component>, Component> componentMap = new HashMap<>();


    private ComponentManager() {
    }

    public static ComponentManager initialize(PsiApplication psiApplication) {
        if (instance == null) {
            instance = new ComponentManager();
        }
        instance.app = psiApplication;
        instance.initialize();
        return instance;
    }

    public static ComponentManager getInstance() {
        return instance;
    }

    public <T extends Component> T getComponent(Class<T> clazz) {
        if (componentMap.get(clazz) != null) return (T) componentMap.get(clazz);

        T component = null;

        try {
            component = (T) clazz.getConstructors()[0].newInstance(app);

            componentMap.put(clazz, component);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return component;
    }

    private Symbol[] getAlphabet() {
        String[] alphabet = app.getResources().getStringArray(R.array.alphabet);
        Symbol[] resultList = new Symbol[alphabet.length];
        for (int i = 0; i < alphabet.length; i++) {
            String letter = alphabet[i];
            Symbol symbol = new Symbol(letter.toUpperCase(), letter.toLowerCase());
            resultList[i] = symbol;
        }
        return resultList;
    }

    private void initialize() {
        Locale.setDefault(Locale.ENGLISH);

        SharedPreferences preferences = app.getSharedPreferences("PSI", Application.MODE_PRIVATE);
        String code = preferences.getString("LANG_CODE", Locale.getDefault().getLanguage());

        Symbol[] alphabet = getAlphabet();
        Language language = new Language(code, alphabet);

        Level level = Level.values()[preferences.getInt("LEVEL_PROGRESS", 0)];

        User user = new User();
        user.setLanguage(language);
        user.setLevel(level);

        getComponent(UserComponent.class).setUser(user);
    }


}
