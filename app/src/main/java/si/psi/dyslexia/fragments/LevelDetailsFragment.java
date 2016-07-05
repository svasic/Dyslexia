package si.psi.dyslexia.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import si.psi.dyslexia.R;
import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.interfaces.MenuItemClickListener;

public class LevelDetailsFragment extends Fragment {

    public static final String TAG = "LevelDetailsFragment";

    private Level level;
    private MenuItemClickListener listener;

    public LevelDetailsFragment() {
    }

    public static LevelDetailsFragment newInstance(Level level) {
        LevelDetailsFragment fragment = new LevelDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("LEVEL", level);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        level = (Level) getArguments().getSerializable("LEVEL");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_details_fragment, container, false);

        TextView txtTitle = (TextView) view.findViewById(R.id.level_title);
        txtTitle.setText(level.getTitle());

        TextView txtDescription = (TextView) view.findViewById(R.id.level_description);
        txtDescription.setText(level.getDescription());

        Button btnStartTest = (Button) view.findViewById(R.id.btn_open_level_test);
        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onStartTestClicked(level);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MenuItemClickListener) {
            listener = (MenuItemClickListener) activity;
        }
    }
}
