package marcelmax.shoppingcart.adapter.viewHolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.view.MainActivity;

import static androidx.navigation.Navigation.findNavController;

public class CartViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_product_name)
    TextView cartProductName;
    @BindView(R.id.tv_quantity)
    TextView cartQuantity;
    @BindView(R.id.tv_total_amount)
    TextView cartTotalAmount;
    @BindView(R.id.tv_date)
    TextView cartDate;
    @BindView(R.id.img_product_image)
    ImageView cartProductImage;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.btn_cart_close)
    public void removeFromCart() {
     //   cartArrayList.remove(getAdapterPosition());
    //    notifyDataSetChanged();

    }

    @OnClick(R.id.cv_cardview)
    public void changeFragment() {
/*
        Bundle bundle = new Bundle();
        MainActivity mainActivity = (MainActivity) mContext;
        final NavController navController = findNavController(mainActivity, R.id.my_nav_host_fragment);

        if (cartArrayList != null) {
            Log.v("********Clicked ", "*****POS. " + getAdapterPosition() + " PRODUCT" + cartArrayList.get(getAdapterPosition()));

            bundle.putParcelable("pass_product", cartArrayList.get(getAdapterPosition()));
            navController.navigate(R.id.action_cartFragment_to_productDetailFragment, bundle);

        } else {
            Log.e("PRODUCTADAPTER", "LIST IS EMPTY");
        }
*/
    }
//todo delete?

}