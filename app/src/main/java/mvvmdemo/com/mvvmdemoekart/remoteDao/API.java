package mvvmdemo.com.mvvmdemoekart.remoteDao;

import java.util.List;


import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.viewmodel.ItemViewModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BaseURL="http://www.mocky.io/v2/";

    @GET("5b3d1da83100002c006de010")
    Call<List<Item>> getItems();
}
