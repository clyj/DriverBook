package cn.ed2k.driverbook.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import cn.ed2k.driverbook.R;
import cn.ed2k.driverbook.request.SignUpRequest;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG ="SignUpActivity";

    private Toolbar mToolbar;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mRePassword;
    private Button mSignUp;
    private SignUpRequest mRequest;

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
        mUsername = (EditText) findViewById(R.id.signup_username);
        mPassword = (EditText) findViewById(R.id.signup_password);
        mRePassword = (EditText) findViewById(R.id.signup_repassword);
        mSignUp = (Button) findViewById(R.id.signup_signup);
    }

    private void initView(){
        mToolbar.setTitle(getResources().getString(R.string.signup_signup));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_whith));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams=getWindow().getAttributes();
            layoutParams.flags=(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|layoutParams.flags);
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_img);
    }

    private void initData(){
        mRequest = new SignUpRequest();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setEvent(){

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupEntry();
            }
        });

    }

    private void signupEntry(){
        String  username = mUsername.getText().toString().trim();
        String  password = mPassword.getText().toString().trim();
        String  rePassword = mRePassword.getText().toString().trim();
        if (username.equals("")){
            return;
        }
        if (password.equals("")){
            return;
        }
        if (rePassword.equals("")){
            return;
        }
        if (password != rePassword || !password.equals(rePassword)){
            return;
        }
        mRequest.setUsername(username);
        mRequest.setUsername(password);


    }
}
