package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sgsits_dr.models.NotificationModel;

public class NotifictionDisplay extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiction_display);
        Bundle bundle=getIntent().getExtras();
        String mes=bundle.getString("description");
        textView=findViewById(R.id.notifdesc);
        textView.setText(mes);
        lottieAnimationView=findViewById(R.id.notiflotti);
        startAnimation();
    }
    public void startAnimation()
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