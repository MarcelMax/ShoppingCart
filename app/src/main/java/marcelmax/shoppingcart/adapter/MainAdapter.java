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
import java.util.List;

import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.model.Address;
import marcelmax.shoppingcart.model.CartItem;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.model.ViewType;
import marcelmax.shoppingcart.view.MainActivity;

import static androidx.navigation.Navigation.findNavController;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // todo https://medium.com/@zackcosborn/display-objects-of-different-types-in-a-recyclerview-8b7d8e3968aa
    private static final int PRODUCT_TYPE = 1;
    private static final int ADDRESS_TYPE = 2;
    private static final int CART_TYPE = 3;

    private int viewType;

    private ArrayList<Product> mProducts;
    private ArrayList<Address> mAddresses;
    private ArrayList<CartItem> mCart;

    private List<ViewType> mViewTypes;

    private Context mContext;

    public MainAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public MainAdapter(Context mContext, ArrayList<Product> productList) {
        this.mContext = mContext;
        this.mProducts = productList;
    }

    public MainAdapter(Context mContext, ArrayList<Address> addressList, ArrayList<CartItem> cartList) {
        this.mContext = mContext;
        this.mAddresses = addressList;
        this.mCart = cartList;
    }


    public void setViewTypeList(List<? extends ViewType> viewTypeList) {
        if (mViewTypes == null){
            mViewTypes = new ArrayList<>();
        }
        mViewTypes.clear();
        mViewTypes.addAll(viewTypeList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {

            case PRODUCT_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
                return new ProductViewHolder(view);
            }

            case ADDRESS_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.address_list_item, viewGroup, false);
                return new AddressViewHolder(view);
            }

            case CART_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
                return new CartViewHolder(view);
            }

            default: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
                return new ProductViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);

        if (itemViewType == PRODUCT_TYPE) {
            if (mProducts == null){
                mProducts = new ArrayList<>();
            }

            for (ViewType product : mViewTypes){
                mProducts.add((Product)product);
            }

            Product product = mProducts.get(i);
            ProductViewHolder productViewHolder = (ProductViewHolder) viewHolder;

            productViewHolder.productPrice.setText(mContext.getString(R.string.tv_price, product.getProductPrice(), product.getProductCurrency()));
            productViewHolder.productName.setText(product.getProductName());
            productViewHolder.productReviewCount.setText(mContext.getResources().getString(R.string.tv_reviewCount, product.getProductReviewcount()));
            productViewHolder.productShortDescription.setText(product.getProductDescriptionShort());
            productViewHolder.productRating.setRating(Float.parseFloat(product.getProductRating().toString()));
            Picasso.get()
                    .load(product.getProductImg())
                    .into(productViewHolder.productImage);
        } else if (itemViewType == ADDRESS_TYPE) {
            if (mAddresses == null){
                mAddresses = new ArrayList<>();
            }

            for (ViewType address : mViewTypes){
                mAddresses.add((Address)address);
            }

            Address address = mAddresses.get(i);
            AddressViewHolder addressViewHolder = (AddressViewHolder) viewHolder;

            addressViewHolder.firstName.setText(address.getFirstName());
            addressViewHolder.lastName.setText(address.getLastName());
            addressViewHolder.streetName.setText(address.getStreetName());
            addressViewHolder.streetNumber.setText(address.getStreetNumber());
            addressViewHolder.postCode.setText(address.getPostCode());
            addressViewHolder.cityName.setText(address.getCityName());
            Picasso.get()
                    .load(address.getAddressImage())
                    .into(addressViewHolder.addressImg);

        } else if (itemViewType == CART_TYPE) {
            if (mCart == null){
                mCart = new ArrayList<>();
            }

            for (ViewType cartitem : mViewTypes){
                mCart.add((CartItem)cartitem);
            }
            CartItem cartItem = mCart.get(i);
            CartViewHolder cartViewHolder = (CartViewHolder) viewHolder;
            cartViewHolder.cartProductName.setText(cartItem.getProduct().getProductName());
            cartViewHolder.cartQuantity.setText(mContext.getResources().getString(R.string.cart_item_list_quantity, cartItem.getProduct().getProductQuantityChoosen()));
            cartViewHolder.cartTotalAmount.setText(mContext.getResources().getString(R.string.cart_item_list_total, (cartItem.getProduct().getProductPrice() * cartItem.getProduct().getProductQuantityChoosen()), cartItem.getProduct().getProductCurrency()));
            cartViewHolder.cartDate.setText(cartItem.getProduct().getProductDate());
            Picasso.get()
                    .load(cartItem.getProduct().getProductImg())
                    .into(cartViewHolder.cartProductImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
    return mViewTypes.get(position).getType();
    }

    @Override
    public int getItemCount() {
       if (mViewTypes == null){
           return 0;
       }
       else {
           return mViewTypes.size();
       }
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

            if (mProducts != null) {
                Log.v("********Clicked ", "*****POS. " + getAdapterPosition() + " PRODUCT" + mProducts.get(getAdapterPosition()));

                bundle.putParcelable("pass_product", mProducts.get(getAdapterPosition()));
                navController.navigate(R.id.action_productFragment_to_productDetailFragment, bundle);

            } else {
                Log.e("PRODUCTADAPTER", "LIST IS EMPTY");
            }

        }

    }

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

            mCart.remove(getAdapterPosition());
            notifyDataSetChanged();


        }

        @OnClick(R.id.cv_cardview)
        public void changeFragment() {

            Bundle bundle = new Bundle();
            MainActivity mainActivity = (MainActivity) mContext;
            final NavController navController = findNavController(mainActivity, R.id.my_nav_host_fragment);

            if (mCart != null) {
                Log.v("********Clicked ", "*****POS. " + getAdapterPosition() + " PRODUCT" + mCart.get(getAdapterPosition()));

               // bundle.putParcelable("pass_product", mCart.get(getAdapterPosition()));
                navController.navigate(R.id.action_cartFragment_to_productDetailFragment, bundle);

            } else {
                Log.e("PRODUCTADAPTER", "LIST IS EMPTY");
            }

        }

    }

}
