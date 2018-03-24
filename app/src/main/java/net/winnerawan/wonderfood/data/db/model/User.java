package net.winnerawan.wonderfood.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
@Entity(nameInDb = "user")
public class User {

    @Expose
    @SerializedName("id")
    @Id
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password_hash")
    @Expose
    private String password_hash;
    @SerializedName("api_key")
    @Expose
    private String api_key;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    @Generated(hash = 1217706801)
    public User(int id, String name, String email, String password_hash,
            String api_key, String status, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.api_key = api_key;
        this.status = status;
        this.created_at = created_at;
    }

    public User(int id, String name, String email, String api_key, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.api_key = api_key;
        this.created_at = created_at;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
