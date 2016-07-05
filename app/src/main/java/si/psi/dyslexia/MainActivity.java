package si.psi.dyslexia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.fragments.LevelDetailsFragment;
import si.psi.dyslexia.fragments.MainFragment;
import si.psi.dyslexia.fragments.TestFragment;
import si.psi.dyslexia.fragments.TestLevelOneFragment;
import si.psi.dyslexia.interfaces.MenuItemClickListener;


public class MainActivity extends AppCompatActivity implements MenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_fragment, new MainFragment(), MainFragment.TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!isDusalPane()) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMenuItemClicked(Level level) {
        boolean isDualPane = isDusalPane();
        int holderId = isDualPane ? R.id.level_content_holder : R.id.main_fragment;

        FragmentManager fm = getSupportFragmentManager();
        TestFragment fragment = (TestFragment) fm.findFragmentByTag(LevelDetailsFragment.TAG);
        if (fragment != null) {
            // TODO: offer continue (if needed)
        }
        else {

        }

//        LevelDetailsFragment

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(holderId, fragment, LevelDetailsFragment.TAG);
        if (!isDualPane) fragmentTransaction.addToBackStack(LevelDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onStartTestClicked(Level level) {
        // check user status for level and offer continue from previous stoped or clean start
        // add proper info to fragment bundle

        Fragment fragment = null;
        switch (level) {
            case LEVEL1: fragment = TestLevelOneFragment.newInstance(level);
        }

        if (fragment != null) {
            boolean isDualPane = isDusalPane();
            int holderId = isDualPane ? R.id.level_content_holder : R.id.main_fragment;

        }
    }

    // based on layout definition of activity_main.xml
    private boolean isDusalPane() {
        return findViewById(R.id.level_content_holder) != null;
    }
}
