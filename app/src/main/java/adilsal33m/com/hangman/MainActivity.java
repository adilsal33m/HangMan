package adilsal33m.com.hangman;

import android.app.ActionBar;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity implements
        MainFragment.mainFragmentInterface{

    Fragment newFragment;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mp = MediaPlayer.create(this, R.raw.bg);
        mp.setLooping(true);
        mp.start();

        setContentView(R.layout.activity_main);
        // Create new fragment and transaction
            newFragment = (Fragment) MainFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.letter_animate,R.anim.letter_animate);
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
            transaction.replace(R.id.container, newFragment);
// Commit the transaction
            transaction.commit();
    }

    @Override
    public void showAnimation(int i) {
        Fragment newFragment = AnimationFragment.newInstance(i);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.letter_animate,R.anim.letter_animate);
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.animation_container,newFragment);
        transaction.addToBackStack(null);
// Commit the transaction
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mp.isPlaying())
            mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying())
            mp.pause();
    }
}
