package marcelmax.shoppingcart.Util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import marcelmax.shoppingcart.model.Product;

public class Testing {

    public static void printRecipes(ArrayList<Product> list, String tag){
        for(Product product: list){
            Log.d(tag, "onChanged: " + product.getProductName());
        }
    }
}
