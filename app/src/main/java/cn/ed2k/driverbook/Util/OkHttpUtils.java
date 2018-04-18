package cn.ed2k.driverbook.Util;



import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
    private static OkHttpClient client;
    private static OkHttpUtils sInstance=null;
    private static final long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000;
    private static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;


    /*
    构造器
     */
    private OkHttpUtils(){
        loging  =  new HttpLoggingInterceptor();
        loging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(new MyInterceptor() )
                .addInterceptor(loging) .build();

    }

    /*
    单例模式封装
     */

    public static OkHttpUtils getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (sInstance == null) {
                sInstance = new OkHttpUtils();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }


    public void setCache(Context appContext) {
        final File baseDir = appContext.getApplicationContext().getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            getOkHttpClient().newBuilder()
                    .cache((new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)));
        }
    }



    /*
    get请求
     */
    public void doGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);

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
        }
        FormBody build = builder.build();
        Request request = new Request.Builder().url(url).post(build).build();
        client.newCall(request).enqueue(callback);
    }

    /*
    Post请求
    json
     */
    public void doPost(String jsonParms,String url,Callback callback){
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParms);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(callback);

    }




}
