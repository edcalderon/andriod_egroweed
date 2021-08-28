package com.andriod.egroweed.model.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlants {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "email",
            entityColumn = "owner"
    )
    public List<Plant> plants;
}
