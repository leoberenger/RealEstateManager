package com.openclassrooms.realestatemanager.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Agent{
    @PrimaryKey(autoGenerate = true) private long id;
    private String agent_familyName;
    private String agent_firstName;
    private String agent_phoneNb;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Agent() { }

    public Agent(String agent_familyName, String agent_firstName, String agent_phoneNb) {
        this.agent_familyName = agent_familyName;
        this.agent_firstName = agent_firstName;
        this.agent_phoneNb = agent_phoneNb;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getAgent_familyName() {
        return agent_familyName;
    }
    public String getAgent_firstName() {
        return agent_firstName;
    }
    public String getAgent_phoneNb() {
        return agent_phoneNb;
    }

    //---------------------------
    //SETTERS
    //--------------------------
    public void setAgent_familyName(String agent_familyName) {
        this.agent_familyName = agent_familyName;
    }
    public void setAgent_firstName(String agent_firstName) {
        this.agent_firstName = agent_firstName;
    }
    public void setAgent_phoneNb(String agent_phoneNb) {
        this.agent_phoneNb = agent_phoneNb;
    }
}
