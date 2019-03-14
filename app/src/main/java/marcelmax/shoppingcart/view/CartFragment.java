package marcelmax.shoppingcart.view;

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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.adapter.MainAdapter;
import marcelmax.shoppingcart.adapter.OnClickListener;
import marcelmax.shoppingcart.model.CartItem;
import marcelmax.shoppingcart.util.RecyclerItemClickListener;
import marcelmax.shoppingcart.model.Address;

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
    public static ArrayList<CartItem> cartArrayList;

    private MainAdapter mainAdapter;
    private Address address;
    public static Address selectedAddress; //todo static?// atm its not really necessary, but if you want to send an invoice this might change

    //todo collapsingtoolbar for this screen

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_screen, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        adjustLayout(newAddressButton, addressRecyclerView, addressArrayList, addressEmptyView, 1);
        adjustLayout(orderButton, cartRecyclerView, cartArrayList, cartEmptyView, 2);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedAddress = addressArrayList.get(position);
                Toast.makeText(getContext(),
                        getResources().getString(R.string.cart_address_chosen,
                                selectedAddress.getFirstName(),
                                selectedAddress.getLastName(),
                                selectedAddress.getStreetName(),
                                selectedAddress.getStreetNumber(),
                                selectedAddress.getPostCode(),
                                selectedAddress.getCityName()
                        ),
                        Toast.LENGTH_SHORT).show();

            }
        }));
    }

    /***
     * method that prepares and handles each recyclerview and events associated with it
     * @param button = the button is needed because its layout adjust to either the rv or the empty view
     * @param recyclerView = rv which should be inflated
     * @param arrayList = list with either addresses or products in cart
     * @param emptyView = a view to be shown when lists are empty
     * @param viewType = decides what view should be inflated
     */
    public void adjustLayout(Button button, RecyclerView recyclerView, ArrayList arrayList, TextView emptyView, int viewType) {
        int viewRuleOne;
        int viewRuleTwo;

        // shows the appropriate views according to the adapter
        if (viewType == 1) {
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
            prepareRecyclerViewMain(recyclerView, viewType);
        }

    }

    /**
     * set the recyclerview
     * @param recyclerView = the according rv for the view
     * @param viewType = decides what view should be inflated
     */
    private void prepareRecyclerViewMain(RecyclerView recyclerView, int viewType) {
        if (mainAdapter == null){
            mainAdapter = new MainAdapter(getContext());
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);

        if (viewType == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mainAdapter.setViewTypeList(addressArrayList);
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mainAdapter.setViewTypeList(cartArrayList);
        }

    }


    @OnClick(R.id.btn_new_address)
    public void addAddress() {
        if (addressArrayList == null) {
            addressArrayList = new ArrayList<>();
        }

        //todo Mockdata hardcoded
        address = new Address("Max", "Mustermann", "Muster Straße", "10", "55555", "Musterstadt", "http://dummyimage.com/113x233.jpg/ff4444/ffffff", "marcel.max.peters@gmail.com");
        addressArrayList.add(address);

        address = new Address("Alexa", "Musterfrau", "Muster Straße", "10", "55555", "Musterstadt", "http://dummyimage.com/113x233.jpg/ff4444/ffffff", "marcel.max.peters@gmail.com");
        addressArrayList.add(address);

        adjustLayout(newAddressButton, addressRecyclerView, addressArrayList, addressEmptyView, 1);

    }

    @OnClick(R.id.btn_order)
    public void orderProducts() {

        //handles the case when the address array is null
        if (addressArrayList == null) {
            Toast.makeText(getContext(),
                    getResources().getString(R.string.cart_no_address_available),
                    Toast.LENGTH_LONG).show();
        }

        //handles the case when the cart array is null
        if (cartArrayList == null) {
            Toast.makeText(getContext(),
                    getResources().getString(R.string.cart_no_products_in_cart),
                    Toast.LENGTH_LONG).show();
        }

        //handles the case when both arrays are initialized
        if (addressArrayList != null && cartArrayList != null) {
            //handles the case when the cart is not null and user removed everything from the cart and tries to order
            if (cartArrayList.isEmpty()) {
                Toast.makeText(getContext(),
                        getResources().getString(R.string.cart_no_products_in_cart),
                        Toast.LENGTH_LONG).show();
            }

            //handles the case when address array is not null but empty (i guess it never is once you add an address, but for saftey reason its good to have)
            if (addressArrayList.isEmpty()) {
                Toast.makeText(getContext(),
                        getResources().getString(R.string.cart_no_address_available),
                        Toast.LENGTH_LONG).show();
            }

            //handles the case when the user has not chosen a address from the list
            if (selectedAddress == null) {
                Toast.makeText(getContext(),
                        getResources().getString(R.string.cart_no_address_chosen),
                        Toast.LENGTH_SHORT).show();
            }

            //handles the case when you can complete a successful order
            else if (!cartArrayList.isEmpty() && !addressArrayList.isEmpty() && selectedAddress != null) {

                Toast.makeText(getContext(),
                        getResources().getString(R.string.cart_order_successfull),
                        Toast.LENGTH_LONG).show();
              /*
                Intent intent = new Intent(Intent.ACTION_SENDTO);

                intent.putExtra(Intent.EXTRA_EMAIL,selectedAddress.getEmailAddress());
                intent.putExtra(Intent.EXTRA_SUBJECT,R.string.cart_your_order);
                intent.putExtra(Intent.EXTRA_TEXT,R.string.mail_text_body);

                startActivity(Intent.createChooser(intent,""));
                */

                //todo things that could be added later: a order history screen or show a dialog if you really want to order
                //todo send mail to the customer for confirmation
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
