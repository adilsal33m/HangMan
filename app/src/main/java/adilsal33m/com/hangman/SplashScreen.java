package adilsal33m.com.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {

    Button mStartButton;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mp = MediaPlayer.create(this, R.raw.bg);
        mp.setLooping(true);
        mp.start();

        mStartButton=(Button)findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.letter_animate);
                mStartButton.startAnimation(anim);
                MediaPlayer m= MediaPlayer.create(getApplicationContext(),R.raw.enter);
                m.start();
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.pulse);
        mStartButton.startAnimation(anim);
        if(!mp.isPlaying()){
            mp.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying()){
            mp.pause();
        }
    }
}
