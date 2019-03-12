package marcelmax.shoppingcart.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import marcelmax.shoppingcart.adapter.MainAdapter;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.viewmodels.ProductFragmentViewModel;

public class ProductFragment extends Fragment {

    @BindView(R.id.rv_products)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private ArrayList<Product> productArrayList;
    private MainAdapter mainAdapter;
    private ProductFragmentViewModel mProductFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mProductFragmentViewModel = ViewModelProviders.of(this).get(ProductFragmentViewModel.class);
        mProductFragmentViewModel.getProducts();

        subscribeObservers();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void subscribeObservers(){
        if (productArrayList == null){
            productArrayList = new ArrayList<>();
        }

        mProductFragmentViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                if (products != null){
                    for (Product product : products){
                        Log.v("***********", "\n PRODUCT: \n" + product + "\n");
                        productArrayList.add(product);
                        prepareRecyclerViewMain();
                    }

                }
                Log.v("***********", "\n PRODUCTLIST: \n" + productArrayList + "\n");
            }

        });

    }

    private void prepareRecyclerViewMain() {
        mainAdapter = new MainAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.setViewTypeList(productArrayList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}


