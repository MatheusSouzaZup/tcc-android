package br.com.bluedot.redevalor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.bluedot.redevalor.MyApplication;

public class Preferences {

    private static final String TAG = Preferences.class.getSimpleName();

    public static final String NO_VALUE = "NO_VALUE";
    public static final String APP_THEME_COLOR = "APP_THEME_COLOR";
    public static final String ASKED_FOR_TOUCH_ID_KEY = "ASKED_FOR_TOUCH_ID_KEY";

    public static final String PUSH_TOKEN = "PUSH_TOKEN";
    public static final String PUSH_DEVICE_ID = "PUSH_DEVICE_ID";
    public static final String PUSH_TYPE = "push_type";

    public static final String MGM_FIRST_SHARED = "mgm_first_shared:";
    public static final String MGM_FIRST_ACCESS = "mgm_first_access:";

    private static SharedPreferences getSharedPrefs() {
        return new ObscuredSharedPreferences(MyApplication.getInstance(), MyApplication.getInstance().getSharedPreferences(TAG, Context.MODE_PRIVATE));
    }

    public static void saveInteger(int value, String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        sharedPref.edit().putInt(key, value).apply();
    }

    public static Integer restoreInteger(String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        return sharedPref.getInt(key, -1);
    }

    public static void saveString(String s, String key) {
        if (s != null) {
            SharedPreferences sharedPref = getSharedPrefs();
            sharedPref.edit().putString(key, s).apply();
        }
    }

    public static void saveLong(Long l, String key) {
        if (l != null) {
            SharedPreferences sharedPref = getSharedPrefs();
            sharedPref.edit().putLong(key, l).apply();
        }
    }

    public static Long restoreLong(String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        return sharedPref.getLong(key, -1);
    }

    public static String restoreString(String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        return sharedPref.getString(key, NO_VALUE);
    }

    public static void saveBoolean(boolean b, String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        sharedPref.edit().putBoolean(key, b).apply();
    }

    public static boolean restoreBoolean(String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        return sharedPref.getBoolean(key, false);
    }

    public static void remove(String key) {
        SharedPreferences sharedPref = getSharedPrefs();
        sharedPref.edit().remove(key).apply();
    }

    public static void saveObject(Object object, String key) {
        String json = new Gson().toJson(object);
        saveString(json, key);
    }

    public static Object restoreObject(String key, Class clazz) {
        String json = restoreString(key);
        if (json.equals(NO_VALUE)) {
            return null;
        } else {
            try {
                return new Gson().fromJson(json, clazz);
            } catch (IllegalStateException | JsonSyntaxException e) {
                return null;
            }
        }
    }

    public static void removeObject(String key) {
        remove(key);
    }

    public static boolean containsObject(String key, Class clazz) {
        return restoreObject(key, clazz) != null;
    }

    public static boolean hasString(String key) {
        return !restoreString(key).equals(NO_VALUE);
    }

    public static void clear() {
        String pushToken = AuthPreferences.getPushToken();

        getSharedPrefs().edit().clear().apply();
        Preferences.saveString(pushToken, Preferences.PUSH_TOKEN);
    }
}