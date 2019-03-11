package marcelmax.shoppingcart.adapter;

import android.content.Context;
import android.os.Bundle;
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

import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.view.MainActivity;

import static androidx.navigation.Navigation.findNavController;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
//todo delete?

    private Context mContext;
    private ArrayList<Product> cartArrayList; //holds the products the user added to the cart

    public CartAdapter(Context mContext, ArrayList<Product> cartArrayList) {
        this.mContext = mContext;
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        Product product = cartArrayList.get(i);
        cartViewHolder.cartProductName.setText(product.getProductName());
        cartViewHolder.cartQuantity.setText(mContext.getResources().getString(R.string.cart_item_list_quantity, product.getProductQuantityChoosen()));
        cartViewHolder.cartTotalAmount.setText(mContext.getResources().getString(R.string.cart_item_list_total, (product.getProductPrice() * product.getProductQuantityChoosen()), product.getProductCurrency()));
        cartViewHolder.cartDate.setText(product.getProductDate());
        Picasso.get()
                .load(product.getProductImg())
                .into(cartViewHolder.cartProductImage);
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

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
            cartArrayList.remove(getAdapterPosition());
            notifyDataSetChanged();

        }

        @OnClick(R.id.cv_cardview)
        public void changeFragment() {

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

        }

    }
}
