package marcelmax.shoppingcart.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import marcelmax.shoppingcart.R;

public class AddressViewHolder extends RecyclerView.ViewHolder {
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
        ButterKnife.bind(this, itemView);
    }

//todo delete?
}
