package marcelmax.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.model.Address;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Context mContext;
    private ArrayList<Address> addressArrayList;

    public AddressAdapter(Context mContext, ArrayList<Address> addressArrayList) {
        this.mContext = mContext;
        this.addressArrayList = addressArrayList;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.address_list_item,viewGroup,false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder addressViewHolder, int i) {
        Address address = addressArrayList.get(i);
        addressViewHolder.firstName.setText(address.getFirstName());
        addressViewHolder.lastName.setText(address.getLastName());
        addressViewHolder.streetName.setText(address.getStreetName());
        addressViewHolder.streetNumber.setText(address.getStreetNumber());
        addressViewHolder.postCode.setText(address.getPostCode());
        addressViewHolder.cityName.setText(address.getCityName());
        Picasso.get()
                .load(address.getAddressImage())
                .into(addressViewHolder.addressImg);
    }

    @Override
    public int getItemCount() {
        return addressArrayList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_address_firstname)
        TextView firstName;
        @BindView(R.id.tv_address_lastname)
        TextView lastName;
        @BindView(R.id.tv_address_streetname)
        TextView streetName;
        @BindView(R.id.tv_address_streetnumber)
        TextView streetNumber;
        @BindView(R.id.tv_address_postcode)
        TextView postCode;
        @BindView(R.id.tv_address_cityname)
        TextView cityName;
        @BindView(R.id.img_address)
        ImageView addressImg;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
