package cn.ed2k.driverbook.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 44525 on 2018/4/17.
 */
public class SignUpRequest {

    private String username;
    private String password;

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Map<String,String> getMap(){
        Map<String,String> object = new HashMap();
        object.put("name",username);
        object.put("ps",password);
        return  object;
    }
}
