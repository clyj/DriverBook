package cn.ed2k.driverbook.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.ed2k.driverbook.R;

public class SignUpActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindView();
        initView();
        initData();
        setEvent();
    }

    private void bindView(){
        mToolbar = (Toolbar) findViewById(R.id.signup_toolbar);
    }

    private void initView(){
        mToolbar.setTitle("注册");
        mToolbar.setTitleTextColor(R.color.text_whith);
        mToolbar.setNavigationIcon(R.mipmap.back_img);
    }

    private void initData(){}

    private void setEvent(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });
    }
}
