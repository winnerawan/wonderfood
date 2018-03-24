package net.winnerawan.wonderfood.data.network.model;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.winnerawan.wonderfood.data.db.model.Category;

public class CategoryResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
