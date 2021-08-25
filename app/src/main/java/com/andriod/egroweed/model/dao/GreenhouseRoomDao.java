package com.andriod.egroweed.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.andriod.egroweed.model.pojo.Greenhouse;

import java.util.List;

@Dao
public interface GreenhouseRoomDao {
    @Query("SELECT * FROM greenhouses")
    List<Greenhouse>  getAll();

    @Query("SELECT * FROM greenhouses WHERE owner = :emailQuery")
    List<Greenhouse> getGreenhouseByEmail(String emailQuery);

    @Query("SELECT * FROM greenhouses WHERE name = :nameQuery")
    Greenhouse getGreenhouseByName(String nameQuery);

    @Insert
    void insertAll(Greenhouse ... greenhouses );

    @Insert
    void insertOne(Greenhouse greenhouse);

    @Update
    void updateList(List<Greenhouse> Greenhouses);

    @Update
    void updateOne(Greenhouse greenhouse);

    @Delete
    void deleteAll(Greenhouse ... greenhouse);

    @Delete
    void deleteOne(Greenhouse greenhouse);

}
