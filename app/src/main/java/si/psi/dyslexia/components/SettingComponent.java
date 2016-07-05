package si.psi.dyslexia.components;

import android.content.SharedPreferences;

import si.psi.dyslexia.PsiApplication;
import si.psi.dyslexia.core.InstanceComponent;

public class SettingComponent extends Component {

    public SettingComponent(PsiApplication application) {
        super(application);
    }

    @Override
    public void fillToPreferences(SharedPreferences.Editor editor) {

    }

}
