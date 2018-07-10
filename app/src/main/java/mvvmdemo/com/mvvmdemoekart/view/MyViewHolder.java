package mvvmdemo.com.mvvmdemoekart.view;

import android.support.v7.widget.RecyclerView;

import mvvmdemo.com.mvvmdemoekart.databinding.ItemDataBinding;
import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.viewmodel.ItemViewModel;


public class MyViewHolder extends RecyclerView.ViewHolder {
    private ItemDataBinding mDataBinding;
    public MyViewHolder(ItemDataBinding dataBinding) {

        super(dataBinding.getRoot());
        this.mDataBinding=dataBinding;
    }

    public void bind(Item item){
        this.mDataBinding.setListItem(item);
    }

    public ItemDataBinding getmDataBinding(){
        return mDataBinding;
    }
}
