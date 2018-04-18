package cn.ed2k.driverbook.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by 44525 on 2018/4/14.
 */
public class App extends Application{

    private SharedPreferences mUserInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData(){
        mUserInfo = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }

    public boolean isLogin(){
        return mUserInfo.contains("token");
    }

    public void setUserName(String username){
        Editor  editor = mUserInfo.edit();
        editor.putString("username",username);
        editor.commit();
    }

    public void setToken(String token){
        Editor editor = mUserInfo.edit();
        editor.putString("token",token);
        editor.commit();
    }

    public String getUserName(){
        return mUserInfo.getString("username","");
    }

    public String getToken(){
        return mUserInfo.getString("token","");
    }



    public void LogOut(){
        Editor  editor = mUserInfo.edit();
        editor.clear();
        editor.commit();
    }


}
