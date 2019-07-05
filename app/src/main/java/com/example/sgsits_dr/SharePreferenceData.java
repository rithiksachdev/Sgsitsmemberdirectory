package com.example.sgsits_dr;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreferenceData {

    /**
     * This method set boolean value in shared preference.
     *
     * @param context - Current context
     * @param key     - String representation the key
     * @param value   - boolean representing the value
     */
    public static void setSharedPrefBoolean(Context context, final String key, final boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    /**
     * Returns String value as per preference key passed.
     *
     * @param context      - Current context
     * @param key          - String representation the key
     * @param defaultValue - boolean representing the default value
     * @return boolean
     */
    public static boolean getSharedPrefBoolean(Context context, final String key, final boolean defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * This method set String value in shared preference.
     *
     * @param context - Current context
     * @param key     - String representation the key
     * @param value   -String representing the default value
     */
    public static void setSharedPrefString(Context context, final String key, final String value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Returns String value as per preference key passed.
     *
     * @param context      - Current context
     * @param key          - String representation the key
     * @param defaultValue -String representing the default value
     * @return String
     */
    public static String getSharedPrefString(Context context, final String key, final String defaultValue) {
        SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(context);
        return token.getString(key, defaultValue);
    }

    public static void setBooleanPreference(Context context, final String key, final boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanPreference(Context context, final String key, final boolean defaultValue) {
        SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(context);
        return token.getBoolean(key, defaultValue);
    }

    public static void clearAllPreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
