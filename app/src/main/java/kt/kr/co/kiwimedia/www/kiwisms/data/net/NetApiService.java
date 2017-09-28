package kt.kr.co.kiwimedia.www.kiwisms.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andy on 2017. 8. 17..
 */

public class NetApiService  {
    private static final String TAG ="NetApiService";
    //private static final String API_URL = "http://andymo.dothome.co.kr/";
    private static final String API_URL = "http://kt.kiwimedia.co.kr/";


    public static retrofit2.Retrofit createRestrofit() {

        //https://stackoverflow.com/questions/35984898/retrofit2-0-gets-malformedjsonexception-while-the-json-seems-correct
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

       return new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}

