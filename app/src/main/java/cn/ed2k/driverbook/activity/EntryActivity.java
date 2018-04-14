package cn.ed2k.driverbook.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;


import cn.ed2k.driverbook.R;

public class EntryActivity extends BaseActivity {


    private ImageView mSplashImage;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_entry);
        animateImage();
    }

    private void animateImage(){
        intent = new Intent(this,MainActivity.class);
        mSplashImage = (ImageView) findViewById(R.id.entry_doge_bg);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage,"scaleX",1f,1.6f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage,"scaleY",1f,1.6f);
        ObjectAnimator animatoral = ObjectAnimator.ofFloat(mSplashImage,"alpha",1,0);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(5000).play(animatorY).with(animatorX).with(animatoral);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
