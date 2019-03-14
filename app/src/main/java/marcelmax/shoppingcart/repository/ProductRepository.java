package marcelmax.shoppingcart.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
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

    // todo repository for address and cart item as well?

    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public LiveData<List<Product>> getProducts() {

        final MutableLiveData<List<Product>> data = new MutableLiveData<>();
        final ArrayList<Product> products = new ArrayList<>();
        /**
         * Description for the problem mentioned in productfragment.
         * By adding an Arraylist which holds all the products and adding
         * it to the MutableLiveData it only returns a single result, instead of multiple
         * results. (due to the way the api is constructed it would return multiple rows)
         */

        // make the retrofit call
        getProductsResponse().enqueue(new Callback<List<ProductDBResponse>>() {

            @Override
            public void onResponse(Call<List<ProductDBResponse>> call, Response<List<ProductDBResponse>> response) {
                if (response != null && response.body() != null) {
                    for (ProductDBResponse productDBResponse : response.body()) {
                        for (Product product: productDBResponse.getProduct()) {
                          //
                           products.add(product);
                        }
                    }
                    data.setValue(products);
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

        Log.v("", "PRODUCTS " + products);
        Log.v("", "DATA " + data.getValue());

        return data;
    }

    // call for the Retrofit query
    public Call<List<ProductDBResponse>> getProductsResponse() {
        return RetrofitInstance.getRecipeApi().
                getProducts(Constants.API_KEY);
    }

}
