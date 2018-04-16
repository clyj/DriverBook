package cn.ed2k.driverbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.ed2k.driverbook.R;

public class LoginActivity extends AppCompatActivity {

    private TextView mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        initView();
        initData();
        setEvent();
    }

    private void bindViews(){
        mSignUp = (TextView) findViewById(R.id.login_signup);
    }

    private void initView() {
    }

    private void initData() {
    }

    private void setEvent(){
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }


}
