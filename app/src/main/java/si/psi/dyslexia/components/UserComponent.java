package si.psi.dyslexia.components;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import si.psi.dyslexia.PsiApplication;
import si.psi.dyslexia.core.Language;
import si.psi.dyslexia.core.User;

public class UserComponent extends Component {

    private User user;

    public UserComponent(PsiApplication application) {
        super(application);
    }

    @Override
    public void fillToPreferences(SharedPreferences.Editor editor) {
        editor.putString("USER_PREF", new Gson().toJson(user));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
