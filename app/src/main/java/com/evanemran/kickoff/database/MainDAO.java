package com.evanemran.kickoff.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.evanemran.kickoff.models.MatchDataFly;

import java.util.List;

public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insertMatch(MatchDataFly matchData);

    @Delete
    void deleteMatch(MatchDataFly matchData);

//    @Query("UPDATE matchData SET title = :title, notes = :note WHERE ID = :id")
//    void update(int id, String title, String note);
//
//    @Query("UPDATE notes SET pin = :pin WHERE ID = :id")
//    void pin(int id, boolean pin);

    @Query("SELECT * FROM matchData")
    List<MatchDataFly> getAllMatches();
}
