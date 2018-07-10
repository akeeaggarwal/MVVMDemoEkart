package mvvmdemo.com.mvvmdemoekart.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.remoteDao.API;
import mvvmdemo.com.mvvmdemoekart.remoteDao.AppCallBackInterface;
import mvvmdemo.com.mvvmdemoekart.repository.ProjectRepository;
import mvvmdemo.com.mvvmdemoekart.utility.AppUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemViewModel extends AndroidViewModel {

    private final LiveData<List<Item>> itemListObservable;

    private ProjectRepository projectRepository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        projectRepository=ProjectRepository.getInstance(application);
        if (AppUtil.isNetworkAvailable(application)) {
            itemListObservable = projectRepository.getItemList();
        } else {
            itemListObservable = projectRepository.getAllItemsOffline();
        }
    }


    public LiveData<List<Item>> getItemListObservable() {
        return itemListObservable;
    }



    public void insert(List<Item> items) { projectRepository.insertOffline(items); }
}





