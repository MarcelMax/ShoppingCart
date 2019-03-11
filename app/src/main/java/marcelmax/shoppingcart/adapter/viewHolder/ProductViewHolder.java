package marcelmax.shoppingcart.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcelmax.shoppingcart.R;

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
/*
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
*/
//todo delete?

    }
}
