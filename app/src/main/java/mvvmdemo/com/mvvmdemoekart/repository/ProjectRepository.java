package mvvmdemo.com.mvvmdemoekart.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.appdao.AppRoomDatabase;
import mvvmdemo.com.mvvmdemoekart.appdao.ItemDao;
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
    private ItemDao mItemDao;
    private MutableLiveData<List<Item>> data;
    private AppRoomDatabase db;

    private ProjectRepository(Application application){

        db=AppRoomDatabase.getDatabase(application);
        mItemDao=db.itemDao();

    }

    public synchronized static ProjectRepository getInstance(Application application) {

        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository(application);
            }
        }

        return projectRepository;
    }


    // Fetching item list from server using Retrofit
    public LiveData<List<Item>> getItemList() {
        data = new MutableLiveData<>();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(API.BaseURL).
                addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(API.class);
        Call<List<Item>> call=api.getItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> itemList=response.body();
                //appCallBackInterface.onSuccess(itemList);
                insertOffline(itemList);

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


    public void insertOffline (List<Item> items) {
        new InsertAsyncTask(mItemDao).execute(items);
    }

    private static class InsertAsyncTask extends android.os.AsyncTask<List<Item>, Void, Void> {

        private ItemDao mAsyncTaskDao;

        InsertAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<Item>... params) {
            mAsyncTaskDao.deleteAll();
            mAsyncTaskDao.insert(params[0]);

            return null;
        }
    }



    private class GetAsyncTask extends AsyncTask<Void, Void, List<Item>> {

        private ItemDao mAsyncTaskDao;

        GetAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Item> doInBackground(final Void... params) {
         //   data.setValue(mItemDao.getAllItems());
            return mItemDao.getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> listLiveData) {
            super.onPostExecute(listLiveData);
            data.setValue(listLiveData);
        }
    }


   public LiveData<List<Item>> getAllItemsOffline() {
       data = new MutableLiveData<>();
      //  data.setValue(mItemDao.getAllItems());
       new GetAsyncTask(mItemDao).execute();
        return data;
    }
}




