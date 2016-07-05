package si.psi.dyslexia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import si.psi.dyslexia.PsiApplication;
import si.psi.dyslexia.R;
import si.psi.dyslexia.components.ComponentManager;
import si.psi.dyslexia.components.UserComponent;
import si.psi.dyslexia.core.Level;
import si.psi.dyslexia.core.Symbol;
import si.psi.dyslexia.core.User;

//TODO: add countdown fragment
public class TestLevelOneFragment extends TestFragment {

    private static final String TAG = "TestLevelOneFragment";

    private ComponentManager componentManager;
    private Level level;
    private User user;
    private List<String> qListAll;  // question list of all possible letters in their lower, upper and numeric representations
    private List<String> qListRemains;  // remaining question letters
    private List<Symbol> alphabet;

    @Bind(R.id.txt_question) TextView txtQuestion;
    @Bind(R.id.txt_question_symbol) TextView txtSymbol;
    @Bind(R.id.layout_answers) GridView gridAnswers;

    @Override
    public Level getLevel() {
        return Level.LEVEL1;
    }

    public static TestLevelOneFragment newInstance(Level level) {
        TestLevelOneFragment fragment = new TestLevelOneFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, level);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PsiApplication application = (PsiApplication) getActivity().getApplication();
        componentManager = application.getComponentManager();

        user = componentManager.getComponent(UserComponent.class).getUser();

        // for every letter, add uppercase, lowercase and its number representation
        qListAll = new LinkedList<>();
        qListRemains = new LinkedList<>();
        Symbol[] alphabet = user.getLanguage().getAlphabet();
        for (int i = 0; i < alphabet.length; i++) {
            Symbol symbol = alphabet[i];
            qListAll.add(symbol.getLowerCase());
            qListAll.add(symbol.getUpperCase());
            qListAll.add(String.valueOf(i));
            qListRemains.add(symbol.getLowerCase());
            qListRemains.add(symbol.getUpperCase());
            qListRemains.add(String.valueOf(i));
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        getArguments();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        componentManager = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_level_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    private void nextQuestion() {
        if (qListRemains != null && qListRemains.size() > 0 ) {
            int randomNext = new Random().nextInt(qListRemains.size()); // random next symbol
            String nextSymbol = qListRemains.remove(randomNext);    // remove random next

            // animate showing next symbol
            txtSymbol.setText(nextSymbol);
            showPossibleAnswers(nextSymbol);
        }
        else {
            // show result of session and save result
            Toast.makeText(getActivity(), "Congradulations!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param rightAnswer
     */
    private void showPossibleAnswers(String rightAnswer) {
        List<String> usedLetters = new ArrayList<>();

        boolean isLetter = user.getLanguage().containsAlphabetLetter(rightAnswer);

        // nakljucno izberi indeks pravilnige rezultata
        int rightAnswerIndex = new Random().nextInt(gridAnswers.getChildCount());
        for (int i = 0; i < gridAnswers.getChildCount(); i++) {
            Button btnAnswer = (Button) gridAnswers.getChildAt(i);
            if (i == rightAnswerIndex) {
                btnAnswer.setText(rightAnswer);
            }
            else {
                String wrongAnswer = qListAll.get(new Random().nextInt(qListAll.size()));

                // če je pravilni rezultat številka potem prikaži napačno številko drugače napačno črko
                if (isLetter) {
                    if (!user.getLanguage().containsAlphabetLetter(wrongAnswer) || wrongAnswer.equals(rightAnswer) || usedLetters.contains(wrongAnswer)) {
                        while (!user.getLanguage().containsAlphabetLetter(wrongAnswer) || usedLetters.contains(wrongAnswer) || wrongAnswer.equals(rightAnswer)) {
                            wrongAnswer = qListAll.get(new Random().nextInt(qListAll.size()));
                        }
                    }
                }
                else {
                    if (user.getLanguage().containsAlphabetLetter(wrongAnswer) || wrongAnswer.equals(rightAnswer) || usedLetters.contains(wrongAnswer)) {
                        while (user.getLanguage().containsAlphabetLetter(wrongAnswer) || usedLetters.contains(wrongAnswer) || wrongAnswer.equals(rightAnswer)) {
                            wrongAnswer = qListAll.get(new Random().nextInt(qListAll.size()));
                        }
                    }
                }

                usedLetters.add(wrongAnswer);
                btnAnswer.setText(wrongAnswer);
            }

            btnAnswer.setTag(i == rightAnswerIndex);
            btnAnswer.setOnClickListener(answersListener);
        }
    }

    private void rightAnswer(View answerView) {
        // stop timing

        // play right answer sound

        // animate right answer effect (waving of button)

        // animate right answer count up
    }

    private void wrongAnswer(View answerView) {
        // play wrong answer sound

        // animate disappearing of view in material way (disappear in a way of increasing wrong answers count)
        answerView.setVisibility(View.GONE);
    }

    private View.OnClickListener answersListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isRightAnswer = (boolean) v.getTag();
            if (isRightAnswer) {
                rightAnswer(v);
            }
            else {
                wrongAnswer(v);
            }
        }
    };
}
