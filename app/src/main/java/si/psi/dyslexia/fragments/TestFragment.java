package si.psi.dyslexia.fragments;

import android.support.v4.app.Fragment;

import si.psi.dyslexia.core.Level;

/**
 * Created by SVasic on 16.2.2016.
 */
public abstract class TestFragment extends Fragment {

    private static final String TAG = "TestFragment";

    public abstract Level getLevel();
}
