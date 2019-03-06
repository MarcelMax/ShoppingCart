package marcelmax.shoppingcart.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.adapter.ProductAdapter;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.model.ProductDBResponse;
import marcelmax.shoppingcart.service.IProductDataService;
import marcelmax.shoppingcart.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    @BindView(R.id.rv_products)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private ArrayList<Product> products;
    private ProductAdapter productAdapter;
    private ArrayList<ProductDBResponse> dbResponseArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        getProducts();
        return rootView;
    }

    /**
     * Call are made from the mockaroo API.
     * The API generates random items each time a call is made.
     */
    private void getProducts() {

        if (products == null) {
            // 1 use Retrofit singleton through API Interface
            IProductDataService productDataService = RetrofitInstance.getService();
            Log.v("***********", "PDS: " + productDataService.toString());

            // 2 access the interface method with the needed apikey and assign it to a call
            Call<List<ProductDBResponse>> call = productDataService.getProducts(this.getString(R.string.api_key));
            Log.v("***********", "Call IS EXECUTED: " + call.isExecuted() + "\n CALL REQUEST " + call.request());

            // 3  enque the call for the response
            call.enqueue(new Callback<List<ProductDBResponse>>() {
                @Override
                public void onResponse(Call<List<ProductDBResponse>> call, Response<List<ProductDBResponse>> productResponse) {
                    products = new ArrayList<>();

                    if (productResponse != null && productResponse.body() != null) {

                        for (ProductDBResponse productDBResponse : productResponse.body()) {
                            Log.v("***********", "DBRESPONSEBODY SIZE: \n" + productResponse.body().size()); // 10 rows
                            Log.v("***********", "DBRESPONSE SIZE: \n" + productDBResponse.getProduct().size()); // 1 result in einer row

                            for (Product p : productDBResponse.getProduct()) {
                                Log.v("***********", "\n PRODUCT: \n" + p + "\n");
                                products.add(p);
                                prepareRecyclerView();

                            }
                            Log.v("***********", "\nProducts: " + products.size() + "\n\n" + products + "\n\n");

                        }

                    }

                }

                @Override
                public void onFailure(Call<List<ProductDBResponse>> call, Throwable t) {
                    Log.v("***ONFAIL", "ONFAIL CALL IS EXECUTED:\n " + call.isExecuted()
                            + "\n CALL REQUEST " + call.request()
                            + "\n THROWABLE " + t);
                }
            });
        } else {
            prepareRecyclerView();
        }

    }

    // sets up the recyclerview
    private void prepareRecyclerView() {
        productAdapter = new ProductAdapter(getContext(), products);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
