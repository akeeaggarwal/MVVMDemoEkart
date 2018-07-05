package mvvmdemo.com.mvvmdemoekart.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import mvvmdemo.com.mvvmdemoekart.viewmodel.ItemViewModel;
import mvvmdemo.com.mvvmdemoekart.databinding.ItemDataBinding;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<ItemViewModel> itemsList;
    private LayoutInflater infater;

    public ItemListAdapter(Context context, List<ItemViewModel> itemsList){
        this.context=context;
        this.itemsList=itemsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (infater==null)
           infater= LayoutInflater.from(context);
        ItemDataBinding dataBinding=ItemDataBinding.inflate(infater,parent,false);
        return new MyViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ItemViewModel itemViewModel=itemsList.get(position);
            holder.bind(itemViewModel);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

}
