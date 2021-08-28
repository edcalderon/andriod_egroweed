package com.andriod.egroweed.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.andriod.egroweed.model.pojo.Plant;

import java.util.List;

@Dao
public interface PlantRoomDao {
    @Query("SELECT * FROM plants")
    List<Plant> getAll();

    @Query("SELECT * FROM plants WHERE owner = :ownerQuery")
    List<Plant> getPlantsByOwner(String ownerQuery);

    @Insert
    void insertAll(Plant ... Plants );
    @Insert
    void insertOne(Plant Plants);

    @Update
    void updateList(List<Plant> Plants);
    @Update
    void updateOne(Plant Plant);

    @Delete
    void deleteAll(Plant ... Plant);

    @Delete
    void deleteOne(Plant Plant);
}
