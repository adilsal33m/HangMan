package adilsal33m.com.hangman;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity implements
        MainFragment.mainFragmentInterface{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create new fragment and transaction
        Fragment newFragment =(Fragment)MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

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
}
