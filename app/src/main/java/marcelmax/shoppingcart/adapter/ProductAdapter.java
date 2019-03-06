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
import android.widget.RatingBar;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<Product> productArrayList; // list of Products that the user can schoose from to add to the cart

    public ProductAdapter(Context mContext, ArrayList<Product> productArrayList) {
        this.mContext = mContext;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = productArrayList.get(i);
        productViewHolder.productPrice.setText(mContext.getResources().getString(R.string.tv_price, product.getProductPrice(), product.getProductCurrency()));
        productViewHolder.productName.setText(product.getProductName());
        productViewHolder.productReviewCount.setText(mContext.getResources().getString(R.string.tv_reviewCount, product.getProductReviewcount()));
        productViewHolder.productShortDescription.setText(product.getProductDescriptionShort());
        productViewHolder.productRating.setRating(Float.parseFloat(product.getProductRating().toString()));
        Picasso.get()
                .load(product.getProductImg())
                .into(productViewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_product_image)
        ImageView productImage;
        @BindView(R.id.rb_star_rating)
        RatingBar productRating;
        @BindView(R.id.tv_product_name)
        TextView productName;
        @BindView(R.id.tv_price)
        TextView productPrice;
        @BindView(R.id.tv_reviewcount)
        TextView productReviewCount;
        @BindView(R.id.tv_short_description)
        TextView productShortDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_cardview)
        public void changeFragment() {

            Bundle bundle = new Bundle();
            MainActivity mainActivity = (MainActivity) mContext;
            final NavController navController = findNavController(mainActivity, R.id.my_nav_host_fragment);

            if (productArrayList != null) {
                Log.v("********Clicked ", "*****POS. " + getAdapterPosition() + " PRODUCT" + productArrayList.get(getAdapterPosition()));

                bundle.putParcelable("pass_product", productArrayList.get(getAdapterPosition()));
                navController.navigate(R.id.action_productFragment_to_productDetailFragment, bundle);

            } else {
                Log.e("PRODUCTADAPTER", "LIST IS EMPTY");
            }

        }

    }
}
