package net.winnerawan.wonderfood.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.winnerawan.wonderfood.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
@Singleton
public class ApiHeader {

    @Expose
    @SerializedName("Authorization")
    private String authorization;

    @Inject
    public ApiHeader(@ApiInfo String apiKey) {
        authorization = apiKey;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
