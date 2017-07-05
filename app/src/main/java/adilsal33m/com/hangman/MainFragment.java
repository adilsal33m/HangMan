package adilsal33m.com.hangman;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    mainFragmentInterface mListener;
    MediaPlayer sound;
    MediaPlayer voice;
    FlexboxLayout fbox;
    Button mTestButton;
    Button mResetButton;
    EditText mEditText;
    TextView mUsedLetters;
    private static String word = "";
    private static int testCount;
    private static int life=5;
    private static ArrayList<String> used = new ArrayList<String>();
    private static ArrayList<Integer> correctGuess=  new ArrayList<Integer>();

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fbox = (FlexboxLayout) getActivity().findViewById(R.id.flex_layout);
        if(word.equals(""))
            word = Words.getWord();
        displayWord();

        mEditText = (EditText) getActivity().findViewById(R.id.get_text);
        mUsedLetters = (TextView) getActivity().findViewById(R.id.used_letter);

        mTestButton = (Button) getActivity().findViewById(R.id.test_button);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().equals("")) {


                    if (used.contains(mEditText.getText().toString().toLowerCase())) {
                        Toast.makeText(getActivity(), "Already used this letter buddy!", Toast.LENGTH_SHORT).show();
                        mEditText.setText("");
                    } else {
                        used.add(mEditText.getText().toString().toLowerCase());
                        updateUsedLetter();
                        ArrayList<Integer> num = new ArrayList<Integer>();
                        int n = 0;
                        while (true) {
                            n = word.indexOf(mEditText.getText().toString().toLowerCase(), n) + 1;
                            if (n == 0)
                                break;
                            else {
                                num.add(n - 1);
                                correctGuess.add(n-1);
                            }
                        }
                        for (int i : num) {
                            testCount++;
                            TextView t = (TextView) getActivity().findViewById(i);
                            t.setTextColor(Color.parseColor("#ffffffff"));
                            Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.letter_animate);
                            t.startAnimation(anim);
                        }
                        mEditText.setText("");
                        if (num.size() > 0) {
                            checkWin();
                        }
                        else{
                            //Toast.makeText(getApplicationContext(),"Wrong Guess Smartass! B)",Toast.LENGTH_SHORT).show();
                            mListener.showAnimation(--life);
                            changeLifeImage(life);}
                    }
                }
            }
        });


        mResetButton = (Button) getActivity().findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetWord();
            }
        });

        restoreWord();
    }

    private void playWrongSound() {
        sound=MediaPlayer.create(getActivity(),R.raw.wrong);
        sound.start();
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                voice=MediaPlayer.create(getActivity(),R.raw.gasp);
                voice.start();
            }
        });
    }

    private void playRightSound() {
        sound=MediaPlayer.create(getActivity(),R.raw.yes);
        sound.start();
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                voice=MediaPlayer.create(getActivity(),R.raw.do_it);
                voice.start();
            }
        });
    }

    private void playDieSound() {
        voice=MediaPlayer.create(getActivity(),R.raw.die);
        voice.start();
        voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sound=MediaPlayer.create(getActivity(),R.raw.neck_snap);
                sound.start();
            }
        });
    }

    private void playWinSound() {
        voice=MediaPlayer.create(getActivity(),R.raw.woohoo);
        voice.start();
        voice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sound=MediaPlayer.create(getActivity(),R.raw.yes);
                sound.start();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetWord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    private void restoreWord(){
        changeLifeImage(life);
        for (int i : correctGuess) {
            TextView t = (TextView) getActivity().findViewById(i);
            t.setTextColor(Color.parseColor("#ffffffff"));
        }
        restoreUsedLetters();
    }

    private void restoreUsedLetters() {
        mUsedLetters.setText("");
        for (String s:used) {
            if (mUsedLetters.getText().toString().equals(""))
                mUsedLetters.setText(s);
            else
                mUsedLetters.setText(mUsedLetters.getText() + "," + s);
        }
    }

    private void checkWin() {
        if (testCount == word.length()) {
           // Toast.makeText(getActivity(), "You guessed the word correctly! :)\nThe word was " + word, Toast.LENGTH_LONG).show();
            mTestButton.setEnabled(false);
            mTestButton.setTextColor(Color.DKGRAY);
            mTestButton.setBackgroundResource(R.drawable.button_shape_2);
            playWinSound();
        } else {
            playRightSound();
            Toast.makeText(getActivity(), "Only " + (word.length() - testCount) + " more letter" + ((word.length() - testCount) < 2 ? "" : "s") + " to go!", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetWord() {
        mUsedLetters.setText("");
        correctGuess.clear();
        used.clear();
        word = Words.getWord();
        testCount = 0;
        life=5;
        mTestButton.setEnabled(true);
        mTestButton.setBackgroundResource(R.drawable.button_shape);
        mTestButton.setTextColor(Color.WHITE);
        fbox.removeAllViews();
        displayWord();
    }

    private void showWord(){
        for (int i=0;i< word.length();i++){
            used.add(""+word.charAt(i));
            TextView t = (TextView) getActivity().findViewById(i);
            t.setTextColor(Color.parseColor("#ffffffff"));
            Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.letter_animate);
            t.startAnimation(anim);
        }
    }
    private void updateUsedLetter() {
        if (mUsedLetters.getText().toString().equals(""))
            mUsedLetters.setText(mEditText.getText());
        else
            mUsedLetters.setText(mUsedLetters.getText() + "," + mEditText.getText().toString().toLowerCase());
    }


    private void changeLifeImage(int i) {
        ImageView img= (ImageView) getActivity().findViewById(R.id.life_image);
        switch(i){
            case 0:
                showWord();
                playDieSound();
                mTestButton.setEnabled(false);
                mTestButton.setTextColor(Color.DKGRAY);
                mTestButton.setBackgroundResource(R.drawable.button_shape_2);
                img.setBackgroundResource(R.drawable.state5_6);
                break;

            case 1:
                img.setBackgroundResource(R.drawable.state4_11);
                playWrongSound();
                break;

            case 2:
                img.setBackgroundResource(R.drawable.state3_11);
                playWrongSound();
                break;

            case 3:
                img.setBackgroundResource(R.drawable.state2_10);
                playWrongSound();
                break;

            case 4:
                img.setBackgroundResource(R.drawable.state1_10);
                playWrongSound();
                break;
            default:
                break;
        }
    }

    private void displayWord() {
        for (int i = 0; i < word.length(); i++) {
            TextView textView = new TextView(getActivity());
            textView.setText("" + word.charAt(i));
            textView.setId(i);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
            textView.setWidth(80);
            textView.setAllCaps(true);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.letter_bg);
            textView.setScaleX(0.8f);
            textView.setTextColor(Color.parseColor("#00ffffff"));
            fbox.addView(textView);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener=(mainFragmentInterface)context;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public interface mainFragmentInterface{
        public void showAnimation(int i);
    }
}

