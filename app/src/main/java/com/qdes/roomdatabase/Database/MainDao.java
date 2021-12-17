package com.qdes.roomdatabase.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.qdes.roomdatabase.Model.UserData;

import java.util.List;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(UserData userData);

    @Delete
    void delete(UserData userData);

    @Delete
    void reset(List<UserData> userData);

    @Query("UPDATE user_table SET name=:sText WHERE ID=:sID")
    void update(int sID, String sText);


    @Query("SELECT * FROM user_table")
    List<UserData> getAll();


}
