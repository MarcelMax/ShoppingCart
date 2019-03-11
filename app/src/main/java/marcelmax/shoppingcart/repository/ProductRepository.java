package marcelmax.shoppingcart.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import marcelmax.shoppingcart.util.Constants;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.model.ProductDBResponse;
import marcelmax.shoppingcart.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * uses Singelton Pattern
 */
public class ProductRepository {

    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public LiveData<List<Product>> getProducts() {

        final MutableLiveData<List<Product>> data = new MutableLiveData<>();

        // make the retrofit call
        getProductsResponse().enqueue(new Callback<List<ProductDBResponse>>() {

            @Override
            public void onResponse(Call<List<ProductDBResponse>> call, Response<List<ProductDBResponse>> response) {
                if (response != null && response.body() != null) {
                    for (ProductDBResponse productDBResponse : response.body()) {
                        data.setValue(productDBResponse.getProduct());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<ProductDBResponse>> call, Throwable t) {
                Log.v("***ONFAIL", "ONFAIL CALL IS EXECUTED:\n " + call.isExecuted()
                        + "\n CALL REQUEST " + call.request()
                        + "\n THROWABLE " + t);
                data.setValue(null);
            }

        });

        Log.v("", "" + data);

        return data;
    }

    // call for the Retrofit query
    public Call<List<ProductDBResponse>> getProductsResponse() {
        return RetrofitInstance.getRecipeApi().
                getProducts(Constants.API_KEY);
    }

}
