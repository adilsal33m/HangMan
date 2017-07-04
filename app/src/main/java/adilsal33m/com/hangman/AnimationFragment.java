package adilsal33m.com.hangman;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationFragment extends Fragment {


    FrameLayout fLayout;
    private static String ANIMATION_STATE="ANIM_STATE";
    ImageView mImage;
    AnimationDrawable mImageAnimation;
    Handler h;


    public AnimationFragment() {
        // Required empty public constructor
    }

    public static  AnimationFragment newInstance(int i) {

        Bundle args = new Bundle();
        AnimationFragment fragment = new AnimationFragment();
        args.putInt(ANIMATION_STATE,i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        //Image Animation
        mImage = (ImageView) getActivity().findViewById(R.id.animation);
        Bundle b= this.getArguments();
        switch(b.getInt(ANIMATION_STATE)){
            case 0:
                mImage.setBackgroundResource(R.drawable.animate_hang_5);
                break;
            case 1:
                mImage.setBackgroundResource(R.drawable.animate_hang_4);
                break;
            case 2:
                mImage.setBackgroundResource(R.drawable.animate_hang_3);
                break;
            case 3:
                mImage.setBackgroundResource(R.drawable.animate_hang_2);
                break;
            case 4:
                mImage.setBackgroundResource(R.drawable.animate_hang_1);
                break;

            default:
                break;
        }
        playAnimation();
        h= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                    getActivity().getSupportFragmentManager().popBackStack();
            }
        },3000);
    }

    private void playAnimation() {
        mImageAnimation = (AnimationDrawable) mImage.getBackground();
        //if (mImageAnimation.isRunning())
        //    mImageAnimation.stop();
        mImageAnimation.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        h.removeCallbacksAndMessages(null);
    }
}

