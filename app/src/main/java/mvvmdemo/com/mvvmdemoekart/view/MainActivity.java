package mvvmdemo.com.mvvmdemoekart.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.R;
import mvvmdemo.com.mvvmdemoekart.model.Item;
import mvvmdemo.com.mvvmdemoekart.utility.AppUtil;
import mvvmdemo.com.mvvmdemoekart.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvItems;
    private LinearLayoutManager linearLayoutManager;
    private ItemListAdapter adapter;
    private ProgressBar progressBar;
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItems=(RecyclerView) findViewById(R.id.rv_items);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);
        linearLayoutManager=new LinearLayoutManager(this);
        rvItems.setLayoutManager(linearLayoutManager);
        rvItems.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        setDataInAdapter(itemViewModel);
    }

    // fetch data from viewmodel and set in adapter
    private void setDataInAdapter(ItemViewModel itemViewModel) {

        if (!AppUtil.isNetworkAvailable(this)){
            Toast.makeText(this,R.string.network_err,Toast.LENGTH_LONG).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        itemViewModel.getItemListObservable().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                if (items!=null){
                    adapter=new ItemListAdapter(getApplicationContext(),items);
                    rvItems.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }


}
