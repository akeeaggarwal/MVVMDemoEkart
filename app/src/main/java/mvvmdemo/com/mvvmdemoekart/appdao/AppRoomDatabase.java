package mvvmdemo.com.mvvmdemoekart.appdao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mvvmdemo.com.mvvmdemoekart.model.Item;

@Database(entities = {Item.class}, version=1)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static AppRoomDatabase dbINSTANCE;

   public static AppRoomDatabase getDatabase(final Context context) {
        if (dbINSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (dbINSTANCE == null) {
                    dbINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "cart_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return dbINSTANCE;
    }
}
