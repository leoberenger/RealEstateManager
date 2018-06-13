package com.openclassrooms.realestatemanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.openclassrooms.realestatemanager.models.Agent;

@Dao
public interface AgentDAO {

    interface UserDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void createAgent(Agent agent);

        @Query("SELECT * FROM Agent WHERE id = :agentId")
        LiveData<Agent> getAgent(long agentId);
    }
}
