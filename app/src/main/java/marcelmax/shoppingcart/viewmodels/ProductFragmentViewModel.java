package marcelmax.shoppingcart.viewmodels;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.model.ProductDBResponse;
import marcelmax.shoppingcart.repository.ProductRepository;
import marcelmax.shoppingcart.service.IProductDataService;
import marcelmax.shoppingcart.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragmentViewModel extends ViewModel {

    private LiveData<List<Product>> mProducts;
    private ProductRepository mProductRepository;


    //call this method to get the data
    public LiveData<List<Product>> getProducts() {
        if (this.mProducts == null){
            mProductRepository = ProductRepository.getInstance();
            //mProducts = mProductRepository.getProducts();
            mProducts = mProductRepository.getProductsFromRepo();
        }

        return mProducts;
    }


}
