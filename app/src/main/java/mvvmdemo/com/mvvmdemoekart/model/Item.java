package mvvmdemo.com.mvvmdemoekart.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


@Entity(tableName ="ecart_item")
public class Item{
    public String itemName;
    public String itemDescription;
    public String ItemDelivery;
    @PrimaryKey
    @NonNull
    public String itemId;
    public String itemPrice;
    public String itemUrl;



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


}
