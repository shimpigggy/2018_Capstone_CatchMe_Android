package org.techtown.capstoneproject;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by ShimPiggy on 2018-05-25.
 */

@SuppressWarnings("static-access")
public class SharedPreferencesUtil {

    //Boolean 값을 가져오기
    public static boolean getBooleanPreferences(Context context, String name, String key) {
        SharedPreferences p = context.getSharedPreferences(name, context.MODE_PRIVATE);
        return p.getBoolean(key, false);
    }

    //Boolean 값 저장
    public static void saveBooleanPreferences(Context context, String name, String key, boolean value) {
        SharedPreferences p = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //email 값을 가져오기
    public static String getEmailPreferences(Context context, String name, String key) {
        SharedPreferences p = context.getSharedPreferences(name, context.MODE_PRIVATE);
        return p.getString(key, "해당 이메일 없음");
    }

    //email 값 저장
    public static void saveEmailPreferences(Context context, String name, String key, String email) {
        SharedPreferences p = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putString(key, email);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public static void removePreferences(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    public static void removeAllPreferences(Context context, String name) {
        SharedPreferences pref = context.getSharedPreferences(name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}