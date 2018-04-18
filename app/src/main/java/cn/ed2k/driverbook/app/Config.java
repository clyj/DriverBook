package cn.ed2k.driverbook.app;

/**
 * Created by 44525 on 2018/4/17.
 */
public class Config {
    private static  String  URL = "http://208.167.253.38/api";
    private static  String  CREATE = "/user/create";
    private static  String  LOGIN = "/user/login";


    public static String getCREATE() {
        return URL+CREATE;
    }

    public static String getLOGIN() {
        return URL+LOGIN;
    }
}
