package marcelmax.shoppingcart.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import marcelmax.shoppingcart.R;
import marcelmax.shoppingcart.adapter.ViewPagerAdapter;
import marcelmax.shoppingcart.model.Product;
import marcelmax.shoppingcart.model.ProductImage;

import static com.travijuu.numberpicker.library.Enums.ActionEnum.INCREMENT;

public class ProductDetailFragment extends Fragment {

    private static final Object ActionEnum = null;
    private Unbinder unbinder;
    @BindView(R.id.tv_product_name)
    TextView productname;
    @BindView(R.id.rb_star_rating)
    RatingBar productRating;
    @BindView(R.id.tv_reviewcount)
    TextView productReviewCount;
    @BindView(R.id.tv_price)
    TextView productPrice;
    @BindView(R.id.tv_description)
    TextView productDescription;
    @BindView(R.id.number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<String> imagesList;
    private Product product;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_list_item_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillWithContent();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), imagesList);
        viewPager.setAdapter(viewPagerAdapter);
    }


    /**
     * fetches the product and populate the view with content
     */
    private void fillWithContent() {
        // Product product;
        Bundle bundle = this.getArguments();

        imagesList = new ArrayList<>();
        if (bundle != null) {
            product = bundle.getParcelable("pass_product");
            productRating.setRating(Float.parseFloat(product.getProductRating().toString()));
            productname.setText(product.getProductName());
            productReviewCount.setText(getResources().getString(R.string.tv_reviewCount, product.getProductReviewcount()));
            productPrice.setText(getResources().getString(R.string.tv_price, product.getProductPrice(), product.getProductCurrency()));
            productDescription.setText(product.getProductDescription());
            numberPicker.setMax(product.getProductQuantity());
            addImagesToList(product);

        } else {
            Log.v("", "BUNDLE IS NULL");
        }
    }

    /**
     * Adds the image from the previous screen and the previewimages to a list
     * so they can be displayed in a viewpager
     *
     * @param product
     */
    private void addImagesToList(Product product) {
        imagesList.add(product.getProductImg());
        for (ProductImage images : product.getProductImages()) {
            imagesList.add(images.getPreviewImages());
        }
        Log.v("", "" + imagesList);
    }
    //todo handle add to cart button

    @OnClick(R.id.btn_add_to_cart)
    public void addToCart(){
        Log.v("","max quantity before" + product.getProductQuantity());

        if (CartFragment.cartArrayList == null){
            CartFragment.cartArrayList = new ArrayList<>();
        }

        if (CartFragment.cartArrayList.contains(product)){
            Log.v("","IS ALREADY IN LIST" + product);
            CartFragment.cartArrayList.remove(product);
        }

        product.setProductQuantityChoosen(numberPicker.getValue());
        CartFragment.cartArrayList.add(product);


        //todo handle max quantity setting
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
