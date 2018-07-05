package mvvmdemo.com.mvvmdemoekart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.utility.AppUtil;
import mvvmdemo.com.mvvmdemoekart.viewmodel.ItemViewModel;
import mvvmdemo.com.mvvmdemoekart.R;
import mvvmdemo.com.mvvmdemoekart.remoteDao.AppCallBackInterface;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvItems;
    private LinearLayoutManager linearLayoutManager;
    private ItemListAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItems=(RecyclerView) findViewById(R.id.rv_items);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        linearLayoutManager=new LinearLayoutManager(this);
        rvItems.setLayoutManager(linearLayoutManager);
        rvItems.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        setDataInAdapter();
    }

    // fetch data from viewmodel and set in adapter
    private void setDataInAdapter() {

        if (!AppUtil.isNetworkAvailable(this)){
            Toast.makeText(this,R.string.network_err,Toast.LENGTH_LONG).show();
            return;
        }


        new ItemViewModel().getItemList(new AppCallBackInterface() {
            @Override
            public void onSuccess(Object object) {
                List<ItemViewModel> list=(List<ItemViewModel>)object;
                adapter=new ItemListAdapter(MainActivity.this,list);
                rvItems.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Object object) {
                progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,(String)object,Toast.LENGTH_LONG).show();
            }
        });
    }


}
