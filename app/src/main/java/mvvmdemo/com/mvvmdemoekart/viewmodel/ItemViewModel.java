package mvvmdemo.com.mvvmdemoekart.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.remoteDao.API;
import mvvmdemo.com.mvvmdemoekart.remoteDao.AppCallBackInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemViewModel extends BaseObservable {

    private String itemName;
    private String itemDescription;
    private String ItemDelivery;
    private String itemId;
    private String itemPrice;
    private String itemUrl;

    public ItemViewModel(Item item){
        itemName=item.itemName;
        itemDescription=item.itemDescription;
        ItemDelivery=item.ItemDelivery;
        itemId=item.itemId;
        itemPrice=item.itemPrice;
        itemUrl=item.itemUrl;
    }

    public ItemViewModel(){

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDelivery() {
        return ItemDelivery;
    }

    public void setItemDelivery(String itemDelivery) {
        ItemDelivery = itemDelivery;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }



    @BindingAdapter({"bind:itemUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);

    }


    @BindingAdapter({"bind:itemPrice"})
    public static void setPrice(TextView view, String price) {
        view.setText("Rs. " +price);
    }


    @BindingAdapter({"bind:ItemDelivery"})
    public static void setDelivery(TextView view, String delivery) {
        view.setText("- " +delivery);
    }


    @BindingAdapter({"bind:itemDescription"})
    public static void setDesc(TextView view, String itemDescription) {
        view.setText("- " +itemDescription);
    }


    // Fetching item list from server using Retrofit
    public void getItemList(final AppCallBackInterface appCallBackInterface) {

        Retrofit retrofit=new Retrofit.Builder().baseUrl(API.BaseURL).
                addConverterFactory(GsonConverterFactory.create()).build();
        API api=retrofit.create(API.class);
        Call<List<ItemViewModel>> call=api.getItems();

        call.enqueue(new Callback<List<ItemViewModel>>() {
            @Override
            public void onResponse(Call<List<ItemViewModel>> call, Response<List<ItemViewModel>> response) {
                List<ItemViewModel> itemList=response.body();
                appCallBackInterface.onSuccess(itemList);
            }

            @Override
            public void onFailure(Call<List<ItemViewModel>> call, Throwable t) {
                appCallBackInterface.onFailure(t.getMessage());
            }
        });
    }


}
