package marcelmax.shoppingcart.service;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
  //  private static Retrofit retrofit = null;
    private static String BASE_URL = "https://my.api.mockaroo.com/";

    //setup retrofit
    public static IProductDataService getService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            Log.v("***********BASEURL", "RETROFITINSTANCE: " + BASE_URL.toString());

        }
        // return retrofit instance with create and interface
        return retrofit.create(IProductDataService.class);
    }

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static IProductDataService recipeApi = retrofit.create(IProductDataService.class);

    public static IProductDataService getRecipeApi(){
        return recipeApi;
    }

}
