package cn.ed2k.driverbook.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import cn.ed2k.driverbook.R;
import cn.ed2k.driverbook.Util.OkHttpUtils;
import cn.ed2k.driverbook.app.App;
import cn.ed2k.driverbook.app.Config;
import cn.ed2k.driverbook.request.LoginRequest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity";

    private TextView mSignUp;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private LoginRequest mRequest;
    private ProgressBar mBar;
    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApp = (App) getApplication();
        isRun();
        bindViews();
        initView();
        initData();
        setEvent();
    }

    private void isRun(){
        if (mApp.isLogin()){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void bindViews(){
        mSignUp = (TextView) findViewById(R.id.login_signup);
        mUsername = (EditText) findViewById(R.id.login_username);
        mPassword = (EditText) findViewById(R.id.login_password);
        mLogin = (Button) findViewById(R.id.login_login);
    }

    private void initView() {
    }

    private void initData() {
        mRequest = new LoginRequest();
    }

    private void setEvent(){
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEntry();
            }
        });

        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mUsername.length()!=0 && mPassword.length()!=0){
                    mLogin.setEnabled(true);
                } else {
                    mLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mPassword.length()!=0 && mUsername.length()!=0){
                    mLogin.setEnabled(true);
                } else {
                    mLogin.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void LoginEntry(){
        final String  username = mUsername.getText().toString().trim();
        String  password = mPassword.getText().toString().trim();
        if (username.equals("")){
            return;
        }
        if (password.equals("")){
            return;
        }

        mRequest.setUsername(username);
        mRequest.setPassword(password);

        OkHttpUtils.getInstance().doPost(mRequest.getMap(), Config.getLOGIN(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("err", e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("body", response.body().string());
//                String cookie = response.headers().values("Set-Cookie").get(0);
//                Log.i("token", cookie.substring(cookie.indexOf("=")+1,cookie.indexOf(";")));
                if (response.isSuccessful()){
                    if (response.body().string().equals("233")){
                        String cookie = response.headers().values("Set-Cookie").get(0);
                        String token = cookie.substring(cookie.indexOf("=")+1,cookie.indexOf(";"));
                        mApp.setToken(token);
                        mApp.setUserName(username);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });

    }


    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirm, null);
        builder.show();
    }


}
