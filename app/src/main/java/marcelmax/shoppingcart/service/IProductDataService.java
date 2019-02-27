package marcelmax.shoppingcart.service;

import java.util.List;

import marcelmax.shoppingcart.model.ProductDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IProductDataService {
    @GET("arrayproduct.json")
    Call<List<ProductDBResponse>> getProducts(@Query("key")String apiKey);
}
