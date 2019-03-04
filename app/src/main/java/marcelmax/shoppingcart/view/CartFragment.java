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
import marcelmax.shoppingcart.model.Address;

public class CartFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.rv_address)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty_view_address)
    TextView emptyView;
    @BindView(R.id.btn_new_address)
    Button newAddressButton;

    // addressArrayList = new ArrayList<Address>();;
    public static ArrayList<Address> addressArrayList;
    private AddressAdapter addressAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_screen, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        adjustAddressLayout();

        return rootView;
    }

    private void adjustAddressLayout(){
        //todo
        //define the behaviour of the btn adjustment according to which view is visible
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) newAddressButton.getLayoutParams();
        if (addressArrayList == null || addressArrayList.isEmpty() ){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            params.addRule(RelativeLayout.BELOW, R.id.tv_empty_view_address);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            params.addRule(RelativeLayout.BELOW, R.id.rv_address);
            prepareRecyclerView();

        }

    }

    private void prepareRecyclerView() {
            addressAdapter = new AddressAdapter(getContext(), addressArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(addressAdapter);
            addressAdapter.notifyDataSetChanged();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_new_address)
    public void addAddress(){
        if (addressArrayList == null){
            addressArrayList = new ArrayList<>();
        }
        Log.v("","ADDRESSADPATER " + addressArrayList);
        Address address = new Address("Marcel","Peters","Buersche Straße", "29", "49074","Osnabrück","http://dummyimage.com/113x233.jpg/ff4444/ffffff");

        addressArrayList.add(address);
        adjustAddressLayout();


    }
}
