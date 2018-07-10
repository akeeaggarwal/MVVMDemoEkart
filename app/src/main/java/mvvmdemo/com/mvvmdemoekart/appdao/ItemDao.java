package mvvmdemo.com.mvvmdemoekart.appdao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mvvmdemo.com.mvvmdemoekart.model.Item;

@Dao
public interface ItemDao {

    @Insert
    void insert(List<Item> item);
    @Query("DELETE FROM ecart_item")
    void deleteAll();
    @Query("SELECT * from ecart_item ORDER BY itemName ASC")
    List<Item> getAllItems();
}
