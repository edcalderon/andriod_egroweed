package com.andriod.egroweed.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andriod.egroweed.model.dao.GreenhouseRoomDao;
import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.dao.PlantRoomDao;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.model.pojo.User;

@Database(entities = {User.class, Greenhouse.class, Plant.class}, version = 18)
public abstract class LocalStorage extends RoomDatabase {
    private static LocalStorage LocalStorage;

    public abstract UserRoomDao userRoomDao();
    public abstract GreenhouseRoomDao greenhouseRoomDao();
    public abstract PlantRoomDao plantRoomDao();
    public static LocalStorage getLocalStorage(final Context context){
        if(LocalStorage == null){
            LocalStorage = Room.databaseBuilder(context,
                    LocalStorage.class,
                    "DCP2021-02-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return LocalStorage;
    }
}
