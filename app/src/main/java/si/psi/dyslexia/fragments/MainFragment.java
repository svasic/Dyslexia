package si.psi.dyslexia.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import si.psi.dyslexia.R;
import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.interfaces.MenuItemClickListener;

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";
    private MenuItemClickListener listener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MenuItemClickListener) {
            listener = (MenuItemClickListener) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        LinearLayout menuLevelHolder = (LinearLayout) view.findViewById(R.id.menu_level_holder);
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL1));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL2));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL3));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL4));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL5));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL6));
        menuLevelHolder.addView(createMenuItem(inflater, menuLevelHolder, Level.LEVEL7));

        return view;
    }

    public View createMenuItem(LayoutInflater inflater, ViewGroup holder, Level level) {
        LinearLayout item = (LinearLayout) inflater.inflate(R.layout.menu_level_item, holder, false);
        TextView txtTitle = (TextView) item.findViewById(R.id.menu_item_title);
        txtTitle.setText(level.getTitle());
        item.setTag(level);
        item.setOnClickListener(onClickListener);
        return item;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Level level = (Level) v.getTag();
            if (listener != null) {
                listener.onMenuItemClicked(level);
            }
        }
    };
}
