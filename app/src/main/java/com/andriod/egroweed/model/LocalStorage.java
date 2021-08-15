package com.andriod.egroweed.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.pojo.User;

@Database(entities = {User.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {
    public abstract UserRoomDao userRoomDao();
    private static LocalStorage LocalStorage;
    public static LocalStorage getLocalStorage(final Context context){
        if(LocalStorage == null){
            LocalStorage = Room.databaseBuilder(context,
                    LocalStorage.class,
                    "DCP2021-02-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return LocalStorage;
    }
}
