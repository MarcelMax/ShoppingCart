package marcelmax.shoppingcart.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.adapter.AddressAdapter;
import marcelmax.shoppingcart.model.Address;

public class CartFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.rv_address)
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_screen, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mockAddress();
        prepareRecyclerView();
        return rootView;
    }


    private ArrayList<Address> addressArrayList;
    private AddressAdapter addressAdapter;

    private void prepareRecyclerView() {
        if (addressArrayList != null ){

            addressAdapter = new AddressAdapter(getContext(), addressArrayList);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(addressAdapter);
            addressAdapter.notifyDataSetChanged();
        }


    }

    private void mockAddress(){
        Address address = new Address("Marcel","Peters","Buersche Straße", "29", "49074","Osnabrück","http://dummyimage.com/113x233.jpg/ff4444/ffffff");
        addressArrayList = new ArrayList<Address>();
        addressArrayList.add(address);
        addressArrayList.add(address);
        addressArrayList.add(address);
        addressArrayList.add(address);
        addressArrayList.add(address);
    }

    //todo show empty recyclerview nach dem adress button soll dem rv eine adresse hinzugefügt werden
}
