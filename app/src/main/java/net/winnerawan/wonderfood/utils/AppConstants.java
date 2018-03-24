package net.winnerawan.wonderfood.utils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";
    public static final String MAIL_TO = "mailto: winnerawan@gmail.com";
    public static final String CONTACT = "Contact";
    public static final String PROFILE_LINK = "https://www.facebook.com/winnerawan";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "wonderfood.db";
    public static final String PREF_NAME = "wonderfood_pref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final LatLng RESTAURANT_LATITUDE = new LatLng(-7.6400455,111.5374716);
    public static final String OLD_MAPS_API = "AIzaSyCjEZCEd6DDQ83vgInPtrwfY57YlcoAE8g";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
