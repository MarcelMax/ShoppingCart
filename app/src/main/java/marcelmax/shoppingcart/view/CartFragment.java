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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.adapter.AddressAdapter;
import marcelmax.shoppingcart.adapter.CartAdapter;
import marcelmax.shoppingcart.model.Address;
import marcelmax.shoppingcart.model.Product;

public class CartFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.rv_address)
    RecyclerView addressRecyclerView;
    @BindView(R.id.tv_empty_view_address)
    TextView addressEmptyView;
    @BindView(R.id.btn_new_address)
    Button newAddressButton;
    @BindView(R.id.rv_cart)
    RecyclerView cartRecyclerView;
    @BindView(R.id.tv_empty_view_cart)
    TextView cartEmptyView;
    @BindView(R.id.btn_order)
    Button orderButton;

    public static ArrayList<Address> addressArrayList;
    private AddressAdapter addressAdapter;

    public static ArrayList<Product> cartArrayList;
    private CartAdapter cartAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_screen, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        adjustLayout(newAddressButton, addressRecyclerView, addressArrayList, addressEmptyView, 1);
        adjustLayout(orderButton, cartRecyclerView, cartArrayList, cartEmptyView, 2);

        return rootView;
    }

    /***
     * method that prepares and handles each recyclerview and events associated with it
     * @param button = the button is needed because its layout adjust to either the rv or the empty view
     * @param recyclerView = rv which should be inflated
     * @param arrayList = list with either addresses or products in cart
     * @param emptyView = a view to be shown when lists are empty
     * @param adapter = adapter for the appropriate rv
     */
    private void adjustLayout(Button button, RecyclerView recyclerView, ArrayList arrayList, TextView emptyView, int adapter) {
        int viewRuleOne;
        int viewRuleTwo;

        // shows the appropriate views according to the adapter
        if (adapter == 1) {
            viewRuleOne = R.id.tv_empty_view_address;
            viewRuleTwo = R.id.rv_address;
        } else {
            viewRuleOne = R.id.tv_empty_view_cart;
            viewRuleTwo = R.id.rv_cart;
        }

        //define the behaviour of the btn adjustment according to which view is visible
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) button.getLayoutParams();
        if (arrayList == null || arrayList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            params.addRule(RelativeLayout.BELOW, viewRuleOne);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            params.addRule(RelativeLayout.BELOW, viewRuleTwo);
            prepareRecyclerView(recyclerView, adapter);

        }

    }

    private void prepareRecyclerView(RecyclerView recyclerView, int adapter) {
        if (adapter == 1) {
            addressAdapter = new AddressAdapter(getContext(), addressArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(addressAdapter);
            addressAdapter.notifyDataSetChanged();

        } else {
            cartAdapter = new CartAdapter(getContext(), cartArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.btn_new_address)
    public void addAddress() {
        if (addressArrayList == null) {
            addressArrayList = new ArrayList<>();
        }
        Log.v("", "ADDRESSADPATER " + addressArrayList);
        Address address = new Address("Marcel", "Peters", "Buersche Straße", "29", "49074", "Osnabrück", "http://dummyimage.com/113x233.jpg/ff4444/ffffff");

        addressArrayList.add(address);
        adjustLayout(newAddressButton, addressRecyclerView, addressArrayList, addressEmptyView, 1);

    }

    @OnClick(R.id.btn_order)
    public void orderProducts() {
        //todo handle click
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //todo nested scrollview to cart screen
}
