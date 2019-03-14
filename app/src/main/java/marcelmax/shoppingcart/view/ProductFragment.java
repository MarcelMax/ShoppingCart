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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    /**
     * The Problem was that, when you entered another Fragment and reentered
     * the product list screen the on changed Method would trigger and add
     * whatever the last respond was.
     * To change that the Product Repository had to be altered. For more information see
     * comment in repository
     */
    private void subscribeObservers() {
        mProductFragmentViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                if (products != null) {
                    prepareRecyclerViewMain();
                    mainAdapter.setViewTypeList(products);
                }
            }

        });

    }

    private void prepareRecyclerViewMain() {
        mainAdapter = new MainAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}


