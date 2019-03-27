package marcelmax.shoppingcart.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import marcelmax.shoppingcart.datasource.ProductDataSource;
import marcelmax.shoppingcart.model.Address;
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
    private ProductDataSource productDataSource;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }


    public LiveData<List<Product>> getProductsFromRepo(){

        if (productDataSource == null){
            productDataSource = new ProductDataSource();
        }
           return productDataSource.getProductsData();
    }



}
