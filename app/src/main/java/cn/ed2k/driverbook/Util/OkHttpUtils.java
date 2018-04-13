package cn.ed2k.driverbook.Util;



import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 44525 on 2018/4/4.
 */
public class OkHttpUtils {

    static HttpLoggingInterceptor loging;
    private static OkHttpUtils utils = null;
    private static OkHttpClient client;
    /*
    构造器
     */
    private OkHttpUtils(){

    }

    /*
    单例模式封装
     */

    public static OkHttpUtils getOkHttpUtils(){
        loging  =  new HttpLoggingInterceptor();
        loging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (utils == null){
            utils = new OkHttpUtils();
            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(loging)
                    .addInterceptor(new MyInterceptor() )
                    .build();
        }

        return utils;
    }

    /*
    get请求
     */
    public void doGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).equals(callback);
    }

    /*
    post请求
    map
     */
    public void doPost(Map<String,String>params,String url,Callback callback ){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String>entry:params.entrySet()){
            if (entry.getValue()!=null){
                builder.add(entry.getKey(),entry.getValue());
            }
            FormBody build = builder.build();
            Request request = new Request.Builder().url(url).post(build).build();
            client.newCall(request).equals(callback);

        }
    }

    /*
    Post请求
    json
     */
    public void doPost(String jsonParms,String url,Callback callback){
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParms);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).equals(callback);

    }




}
