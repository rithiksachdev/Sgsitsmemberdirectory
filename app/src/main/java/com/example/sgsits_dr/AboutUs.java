package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class AboutUs extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        lottieAnimationView=findViewById(R.id.abtanime);
        startCheckAnimation();
    }
    private void startCheckAnimation()
    {
        final ValueAnimator animator=ValueAnimator.ofFloat(0f,1f).setDuration(5000);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress((Float)animation.getAnimatedValue());
            }
        });

        if(lottieAnimationView.getProgress()==0f)
        {
            animator.start();
        }
        else
        {
            lottieAnimationView.setProgress(0f);
        }
    }

}
