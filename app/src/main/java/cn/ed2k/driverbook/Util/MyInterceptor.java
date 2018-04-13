package cn.ed2k.driverbook.Util;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 44525 on 2018/4/5.
 */
public class MyInterceptor implements Interceptor {
    private Request requestProcess;
    private Response proceed;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //如果是GET请求执行这个方法
        if ("GET".equals(request.method())) {
            //为原来的URL添加公共参数
            String newUrl = request.url().toString() + "&source=android";
            Request.Builder builder = request.newBuilder();
            requestProcess = builder.get().url(newUrl).build();
            proceed = chain.proceed(requestProcess);
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            RequestBody body = request.body();
            if (body instanceof FormBody) {
                FormBody formbody = (FormBody) body;
                for (int i = 0; i < formbody.size(); i++) {
                    builder.add(formbody.encodedName(i), formbody.encodedValue(i));
                }
                builder.add("source", "android");
            }
            requestProcess = request.newBuilder().url(request.url().toString()).post(builder.build()).build();
            proceed = chain.proceed(requestProcess);
        }
        return proceed;
    }

    public MyInterceptor() {
        super();
    }
}
