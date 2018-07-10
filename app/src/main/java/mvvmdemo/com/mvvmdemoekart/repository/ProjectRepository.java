package mvvmdemo.com.mvvmdemoekart.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.remoteDao.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    API api;
    private static ProjectRepository projectRepository;

    private ProjectRepository(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(API.BaseURL).
                addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(API.class);
    }

    public synchronized static ProjectRepository getInstance() {

        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }


    // Fetching item list from server using Retrofit
    public LiveData<List<Item>> getItemList() {
        final MutableLiveData<List<Item>> data = new MutableLiveData<>();

        Call<List<Item>> call=api.getItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> itemList=response.body();
                //appCallBackInterface.onSuccess(itemList);
                data.setValue(itemList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
               // appCallBackInterface.onFailure(t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
